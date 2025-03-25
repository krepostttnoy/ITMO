package commands

import baseClasses.ExitFlag
import collection.CollectionManager
import utils.inputOutput.OutputManager
import kotlin.system.exitProcess

/**
 * Команда для завершения работы программы.
 * Выводит сообщение о завершении и завершает процесс с кодом 0.
 *
 * @property cm Менеджер коллекции, переданный для совместимости с другими командами (не используется).
 * @constructor Создаёт команду [ExitCommand] с заданным менеджером [cm].
 */
class ExitCommand(
    private val outputManager: OutputManager,
    ) : Command {

    /**
     * Выполняет команду завершения программы.
     * Выводит сообщение "Программа завершена." и завершает процесс с кодом 0.
     */
    override fun execute() {
        outputManager.disableOutput()
        outputManager.surePrint("Программа завершена.")
        ExitFlag.exitFlag = true
    }
}