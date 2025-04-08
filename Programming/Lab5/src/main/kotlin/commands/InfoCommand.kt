package commands

import collection.CollectionManager

/**
 * Команда для вывода информации о коллекции.
 * Использует [CollectionManager] для получения и вывода информации о коллекции транспортных средств.
 *
 * @property cm Менеджер коллекции, содержащий информацию о коллекции.
 * @constructor Создаёт команду [InfoCommand] с заданным менеджером [cm].
 */
class InfoCommand(private val cm: CollectionManager) : Command {
    override val interactive = false
    /**
     * Выполняет команду вывода информации о коллекции.
     * Вызывает метод [CollectionManager.printCollectionInfo] для отображения информации.
     */
    override fun execute(args: String?) {
        cm.printCollectionInfo()
    }
}