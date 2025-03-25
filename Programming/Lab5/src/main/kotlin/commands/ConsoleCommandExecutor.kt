package commands

import collection.CollectionManager
import console.ConsoleReadManager
import console.ConsoleReadValid
import console.ConsoleVehicleManager
import console.Read
import file.ConsoleFileManager
import file.IFileManager
import utils.inputOutput.InputManager
import utils.inputOutput.OutputManager

/**
 * Класс для выполнения команд из консоли.
 * Хранит набор доступных команд и выполняет их на основе введённой строки.
 * Реализует интерфейс [ICommandExecutor] для управления командами.
 *
 * @property cm Менеджер коллекции, используемый для работы с коллекцией транспортных средств.
 * @property fm Менеджер файлов, используемый для операций с файлами (например, сохранения).
 * @property commands Словарь, содержащий доступные команды с их названиями в качестве ключей.
 * @property console Объект для чтения и валидации данных из консоли.
 * @constructor Создаёт экземпляр [ConsoleCommandExecutor] с заданными менеджерами [cm] и [fm].
 */
class ConsoleCommandExecutor(
    private val cm: CollectionManager,
    private val fm: IFileManager,
    private val inputManager: InputManager,
    private val outputManager: OutputManager
) : ICommandExecutor {

    private val crm = ConsoleReadManager(outputManager, inputManager)
    private val crv = ConsoleReadValid(outputManager, inputManager)
    private val vehicleManager = ConsoleVehicleManager(crm)
    /**
     * Словарь доступных команд.
     * Ключи — названия команд, значения — объекты команд, реализующие [Command].
     */
    private val commands = mapOf(
        "add" to AddCommand(cm, vehicleManager, outputManager),
        "help" to HelpCommand(this),
        "info" to InfoCommand(cm),
        "show" to ShowCommand(cm, outputManager,),
        "exit" to ExitCommand(outputManager),
        "update_id" to UpdateIdCommand(cm, outputManager, inputManager),
        "remove_by_id" to RemoveByIdCommand(cm, outputManager, inputManager),
        "clear" to ClearCommand(cm, outputManager),
        "remove_at" to RemoveAtCommand(cm, crv, outputManager),
        "save" to SaveCommand(fm),
        "add_if_max" to AddIfMaxCommand(cm, vehicleManager, outputManager),
        "remove_greater" to RemoveGreaterCommand(cm, crv, outputManager),
        "avg_of_eng_pw" to AvgOfEnginePowerCommand(cm, outputManager),
        "count_gr_than_eng_pw" to CountGrThanEngPwCommand(cm, crv, outputManager),
        "min_by_fuel_type" to MinByFuelTypeCommand(cm, outputManager),
        "execute_script" to ExecuteScriptCommand(cm, fm, this, outputManager, inputManager),
        "disable_output" to DisableOutputCommand(outputManager),
        "enable_output" to EnableOutputCommand(outputManager)
    )

    /**
     * Выполняет команду на основе введённой строки.
     * Разбирает строку на название команды и аргумент (если есть),
     * затем вызывает метод [Command.execute] у соответствующей команды.
     *
     * @param commandStr Строка, содержащая название команды и, возможно, аргумент.
     */
    override fun executeCommand(commandStr: String) {
        val parts = commandStr.trim().split("\\s+".toRegex())
        val commandName = parts[0].lowercase()
        val arg = if (parts.size > 1) parts[1] else null

        val command = commands[commandName]
        if (command == null) {
            outputManager.surePrint("Unknown command. Available commands: ${commands.keys.joinToString(", ")}")
            return
        }

        when (command) {
            is RemoveByIdCommand -> command.execute(arg)
            is RemoveAtCommand -> command.execute(arg)
            is RemoveGreaterCommand -> command.execute(arg)
            is CountGrThanEngPwCommand -> command.execute(arg)
            is ExecuteScriptCommand -> command.execute(arg)
            else -> command.execute()
        }
    }

    /**
     * Выводит список доступных команд в консоль.
     */
    override fun getHelp() {
        outputManager.surePrint("Доступные команды: ${commands.keys.joinToString(", ").lowercase()}")
    }
}