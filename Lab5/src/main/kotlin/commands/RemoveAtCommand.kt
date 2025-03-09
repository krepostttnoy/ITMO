package commands

import baseClasses.Vehicle
import collection.CollectionManager
import console.ConsoleReadValid
import console.Read

/**
 * Команда для удаления транспортного средства из коллекции по указанному индексу.
 * Использует [CollectionManager] для управления коллекцией и [Read] для чтения данных от пользователя.
 *
 * @property cm Менеджер коллекции, содержащий список транспортных средств.
 * @property rm Объект для чтения данных от пользователя.
 * @constructor Создаёт команду [RemoveAtCommand] с заданными менеджерами [cm] и [rm].
 */
class RemoveAtCommand(
    private val cm: CollectionManager,
    private val rm: Read
) : Command {

    /**
     * Выполняет команду удаления транспортного средства по указанному индексу.
     *
     * @param indexStr Строковое представление индекса элемента для удаления (может быть null).
     */
    fun execute(indexStr: String? = null) {
        if (cm.baseCollection.isEmpty()) {
            println("Коллекция пуста.")
            return
        }

        val index: Int? = if (indexStr == null) {
            print("Введите индекс элемента для удаления (0-${cm.baseCollection.size - 1}): ")
            rm.readInt()
        } else {
            indexStr.toIntOrNull()
        }

        if (index == null || index < 0 || index >= cm.baseCollection.size) {
            println("Неверный индекс")
            return
        }

        val vehicleToRemove = cm.baseCollection[index]
        cm.baseCollection.removeAt(index)
        Vehicle.existingIds.remove(vehicleToRemove.id)
        println("Элемент удален.")
    }

    /**
     * Выполняет команду без аргументов.
     * Вызывает [execute] с параметром [indexStr] равным null,
     * что приводит к запросу индекса у пользователя.
     */
    override fun execute() {
        execute(null)
    }
}