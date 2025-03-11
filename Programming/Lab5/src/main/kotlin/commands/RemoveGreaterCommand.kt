package commands

import baseClasses.Coordinates
import baseClasses.Vehicle
import collection.CollectionManager
import console.ConsoleReadValid
import console.Read

/**
 * Команда для удаления всех транспортных средств с мощностью двигателя больше заданного значения.
 * Использует [CollectionManager] для управления коллекцией и [Read] для чтения данных от пользователя.
 * Сравнение выполняется с помощью метода [Vehicle.compareTo].
 *
 * @property cm Менеджер коллекции, содержащий список транспортных средств.
 * @property r Объект для чтения данных от пользователя.
 * @constructor Создаёт команду [RemoveGreaterCommand] с заданными менеджерами [cm] и [r].
 */
class RemoveGreaterCommand(
    private val cm: CollectionManager,
    private val r: Read
) : Command {

    /**
     * Выполняет команду удаления всех транспортных средств с мощностью двигателя больше заданного значения.
     *
     * Алгоритм:
     * 1. Если коллекция пуста, выводит сообщение и завершает выполнение.
     * 2. Получает значение мощности двигателя из аргумента [enginePowerStr] или запрашивает его у пользователя через [r].
     * 3. Создаёт временный объект [Vehicle] с указанной мощностью для сравнения.
     * 4. Находит все элементы в коллекции, у которых мощность больше заданной.
     * 5. Удаляет найденные элементы из коллекции и из списка использованных ID в [Vehicle.existingIds].
     * 6. Выводит количество удалённых элементов и информацию о каждом удалённом объекте.
     *
     * @param enginePowerStr Строковое представление мощности двигателя для сравнения (может быть null).
     */
    fun execute(enginePowerStr: String? = null) {
        if (cm.baseCollection.isEmpty()) {
            println("Коллекция пуста.")
            return
        }

        val enginePower = enginePowerStr?.toFloatOrNull() ?: run {
            print("Введите enginePower для сравнения: ")
            r.readFloat() ?: return
        }

        val element = Vehicle(
            name = "ComparingModel",
            coordinates = Coordinates(0, 0),
            enginePower = enginePower,
            capacity = 0.0f,
            distanceTravelled = 0,
            fuelType = null
        )

        val toRemove = cm.baseCollection.filter { it > element }

        if (toRemove.isEmpty()) {
            println("Нет элементов, превышающих enginePower заданного.")
            return
        }

        toRemove.forEach { vehicle ->
            cm.baseCollection.remove(vehicle)
            Vehicle.existingIds.remove(vehicle.id)
        }

        println("Удалено ${toRemove.size} элементов.")

        toRemove.forEach { vehicle ->
            println("Удалён объект с enginePower = ${vehicle.enginePower}")
        }
    }

    /**
     * Выполняет команду без аргументов.
     * Вызывает [execute] с параметром [enginePowerStr] равным null,
     * что приводит к запросу значения у пользователя.
     */
    override fun execute() {
        execute(null)
    }
}