package commands

import baseClasses.FuelType
import collection.CollectionManager
import utils.inputOutput.OutputManager

/**
 * Команда для поиска транспортного средства с минимальным значением [FuelType].
 * Использует [CollectionManager] для доступа к коллекции и поиска элемента.
 * Сравнение выполняется на основе естественного порядка значений [FuelType].
 *
 * @property cm Менеджер коллекции, содержащий список транспортных средств.
 * @constructor Создаёт команду [MinByFuelTypeCommand] с заданным менеджером [cm].
 */
class MinByFuelTypeCommand(
    private val cm: CollectionManager,
    private val outputManager: OutputManager
    ) : Command {
    override val interactive = false

    /**
     * Выполняет команду поиска транспортного средства с минимальным значением [FuelType].
     *
     * Алгоритм:
     * 1. Если коллекция пуста, выводит сообщение и завершает выполнение.
     * 2. Ищет транспортное средство с минимальным [FuelType], заменяя null на минимальное значение [FuelType].
     * 3. Если элемент найден, выводит его имя и тип топлива.
     * 4. Если элемент не найден, выводит сообщение об ошибке.
     */
    override fun execute(args: String?) {
        if (cm.baseCollection.isEmpty()) {
            outputManager.println("Коллекция пуста. Нет элементов для поиска минимального fuelType.")
            return
        }

        val minVehicle = cm.baseCollection.minByOrNull { vehicle ->
            vehicle.fuelType ?: FuelType.entries.toTypedArray().min()
        }

        if (minVehicle == null) {
            outputManager.println("Не удалось найти элемент с минимальным fuelType.")
            return
        }

        outputManager.println("${minVehicle.name} -> ${minVehicle.fuelType}")
    }
}