package commands

import collection.CollectionManager
import console.ConsoleReadManager
import console.ConsoleReadValid
import console.ConsoleVehicleManager
import console.Read
import file.ConsoleFileManager
import file.IFileManager

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
    private val fm: IFileManager
) : ICommandExecutor {

    /**
     * Словарь доступных команд.
     * Ключи — названия команд, значения — объекты команд, реализующие [Command].
     */
    private val commands = mapOf(
        "add" to AddCommand(cm, ConsoleVehicleManager(ConsoleReadManager())),
        "help" to HelpCommand(this),
        "info" to InfoCommand(cm),
        "show" to ShowCommand(cm),
        "exit" to ExitCommand(cm),
        "update_id" to UpdateIdCommand(cm),
        "remove_by_id" to RemoveByIdCommand(cm),
        "clear" to ClearCommand(cm),
        "remove_at" to RemoveAtCommand(cm, ConsoleReadValid()),
        "save" to SaveCommand(fm),
        "add_if_max" to AddIfMaxCommand(cm, ConsoleVehicleManager(ConsoleReadManager())),
        "remove_greater" to RemoveGreaterCommand(cm, ConsoleReadValid()),
        "avg_of_eng_pw" to AvgOfEnginePowerCommand(cm),
        "count_gr_than_eng_pw" to CountGrThanEngPwCommand(cm, ConsoleReadValid()),
        "min_by_fuel_type" to MinByFuelTypeCommand(cm),
        "execute_script" to ExecuteScriptCommand(cm, fm, this)
    )

    /**
     * Объект для чтения и валидации данных из консоли.
     */
    val console = ConsoleReadValid()

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
            println("Unknown command. Available commands: ${commands.keys.joinToString(", ")}")
            return
        }

        when (command) {
            is RemoveByIdCommand -> command.execute(arg)
            is RemoveAtCommand -> command.execute(arg)
            is SaveCommand -> command.execute(arg)
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
        println("Доступные команды: ${commands.keys.joinToString(", ").lowercase()}")
    }
}