package commands

import collection.CollectionManager

/**
 * Команда для отображения всех элементов коллекции.
 * Использует [CollectionManager] для получения строкового представления коллекции и вывода его в консоль.
 *
 * @property cm Менеджер коллекции, содержащий список транспортных средств.
 * @constructor Создаёт команду [ShowCommand] с заданным менеджером [cm].
 */
class ShowCommand(private val cm: CollectionManager) : Command {

    /**
     * Выполняет команду отображения элементов коллекции.
     * Вызывает [CollectionManager.printCollection] и выводит результат в консоль.
     */
    override fun execute() {
        println(cm.printCollection())
    }
}