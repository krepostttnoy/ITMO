package commands

import collection.CollectionManager
import file.IFileManager
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.Exception

/**
 * Команда для выполнения скрипта из файла.
 * Читает команды из указанного файла построчно и выполняет их с помощью [ICommandExecutor].
 * Использует [CollectionManager] и [IFileManager] для работы с коллекцией и файлами.
 *
 * @property cm Менеджер коллекции, используемый для выполнения команд.
 * @property fm Менеджер файлов, используемый для операций с файлами.
 * @property ce Исполнитель команд, который выполняет команды из скрипта.
 * @constructor Создаёт команду [ExecuteScriptCommand] с заданными менеджерами [cm], [fm] и [ce].
 */
class ExecuteScriptCommand(
    private val cm: CollectionManager,
    private val fm: IFileManager,
    private val ce: ICommandExecutor
) : Command {

    /**
     * Статический объект для отслеживания выполняемых скриптов.
     * Хранит множество путей к файлам, которые уже выполняются, чтобы избежать рекурсии.
     */
    companion object {
        /**
         * Множество канонических путей к файлам, которые в данный момент выполняются.
         */
        private val executingScr = mutableSetOf<String>()
    }

    /**
     * Выполняет команды из файла, указанного по пути [fileName].
     *
     * Алгоритм:
     * 1. Если [fileName] не указано, запрашивает путь у пользователя.
     * 2. Проверяет существование файла и права на чтение.
     * 3. Проверяет, не выполняется ли уже данный скрипт (для предотвращения рекурсии).
     * 4. Читает файл построчно и выполняет каждую команду с помощью [ce].
     * 5. Выводит сообщения об успехе или ошибках для каждой команды.
     *
     * @param fileName Путь к файлу со скриптом (может быть null, тогда путь запрашивается у пользователя).
     * @throws Exception Если произошла ошибка при чтении файла или выполнении команды.
     */
    fun execute(fileName: String?) {
        val srcPath = fileName ?: run {
            print("Введите путь к файлу: ")
            readLine()?.trim() ?: return
        }

        val file = File(srcPath)
        if (!file.exists()) {
            println("Файл не найден.")
            return
        }

        if (!file.canRead()) {
            println("Нет прав на чтение данного файла.")
            return
        }

        val canonicalPath = file.canonicalPath
        if (executingScr.contains(canonicalPath)) {
            println("Скрипт уже выполняется.")
            return
        }

        executingScr.add(canonicalPath)

        try {
            BufferedInputStream(FileInputStream(file)).use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    var line: String?
                    var lineNumber = 0
                    while (reader.readLine().also { line = it } != null) {
                        lineNumber++
                        val commandStr = line?.trim()

                        if (commandStr.isNullOrEmpty()) {
                            println("Пустая строка пропущена.")
                            continue
                        }

                        println("Выполняется команда из скрипта (строка $lineNumber): $commandStr")
                        try {
                            ce.executeCommand(commandStr)
                        } catch (e: Exception) {
                            println("Ошибка при выполнении команды $commandStr: ${e.message}")
                        }
                    }
                }
            }
        } catch (e: Exception) {
            println("Ошибка при чтении файла: ${e.message}")
        } finally {
            executingScr.remove(canonicalPath)
        }
    }

    /**
     * Выполняет команду без аргументов.
     * Вызывает [execute] с параметром [fileName] равным null,
     * что приводит к запросу пути к файлу у пользователя.
     */
    override fun execute() {
        execute(null)
    }
}