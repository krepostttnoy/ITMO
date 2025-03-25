/**
 * Главный файл приложения для управления коллекцией транспортных средств.
 * Инициализирует менеджеры коллекции, файлов и команд, а затем запускает консольный цикл для ввода и выполнения команд.
 */
import commands.ConsoleCommandExecutor
import collection.CollectionManager
import file.ConsoleFileManager
import utils.Console

/**
 * Точка входа в приложение.
 * Создаёт экземпляры [CollectionManager], [ConsoleFileManager] и [ConsoleCommandExecutor],
 * после чего запускает бесконечный цикл для чтения и выполнения команд из консоли.
 *
 * @throws IllegalArgumentException Если произошла ошибка при инициализации или выполнении команд.
 * @throws StackOverflowError Если возникла рекурсия (например, при выполнении скрипта).
 */
fun main() {
    val console = Console()

    console.startInteractiveMode()
}