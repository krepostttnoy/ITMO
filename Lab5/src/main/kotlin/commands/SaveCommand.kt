package commands

import file.IFileManager

/**
 * Команда для сохранения коллекции в файл.
 * Использует [IFileManager] для выполнения операции сохранения.
 *
 * @property fm Менеджер файлов, используемый для сохранения данных.
 * @constructor Создаёт команду [SaveCommand] с заданным менеджером [fm].
 */
class SaveCommand(private val fm: IFileManager) : Command {

    /**
     * Выполняет команду сохранения коллекции в файл.
     * Если путь к файлу не указан, использует путь по умолчанию, предоставленный [IFileManager].
     *
     * @param filePath Путь к файлу, в который нужно сохранить коллекцию (может быть null).
     */
    fun execute(filePath: String? = null) {
        fm.saveToFile(filePath ?: fm.getFilePath())
    }

    /**
     * Выполняет команду без аргументов.
     * Вызывает [execute] с параметром [filePath] равным null,
     * что приводит к использованию пути по умолчанию.
     */
    override fun execute() {
        execute(null)
    }
}