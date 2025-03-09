package commands

import collection.CollectionManager
import kotlin.system.exitProcess

/**
 * Команда для завершения работы программы.
 * Выводит сообщение о завершении и завершает процесс с кодом 0.
 *
 * @property cm Менеджер коллекции, переданный для совместимости с другими командами (не используется).
 * @constructor Создаёт команду [ExitCommand] с заданным менеджером [cm].
 */
class ExitCommand(private val cm: CollectionManager) : Command {

    /**
     * Выполняет команду завершения программы.
     * Выводит сообщение "Программа завершена." и завершает процесс с кодом 0.
     */
    override fun execute() {
        println("Программа завершена.")
        exitProcess(0)
    }
}