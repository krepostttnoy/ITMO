package commands

import collection.CollectionManager
import console.IVehicleManager
import utils.inputOutput.OutputManager

/**
 * Команда для добавления нового транспортного средства в коллекцию, если оно больше максимального.
 * Сравнение выполняется по мощности двигателя ([Vehicle.enginePower]) с помощью [Vehicle.compareTo].
 * Использует [IVehicleManager] для создания нового объекта [Vehicle] и [CollectionManager] для работы с коллекцией.
 *
 * @property cm Менеджер коллекции, в которую может быть добавлено транспортное средство.
 * @property vm Менеджер для создания нового транспортного средства.
 * @constructor Создаёт команду [AddIfMaxCommand] с заданными менеджерами [cm] и [vm].
 */
class AddIfMaxCommand(
    private val cm: CollectionManager,
    private val vm: IVehicleManager,
    private val outputManager: OutputManager
) : Command {
    override val interactive = true

    /**
     * Выполняет команду добавления нового транспортного средства, если оно больше максимального.
     *
     * Алгоритм:
     * 1. Если коллекция пуста, добавляет новый объект и завершает выполнение.
     * 2. Находит транспортное средство с максимальной мощностью двигателя в коллекции.
     * 3. Создаёт новый объект [Vehicle] с помощью [vm].
     * 4. Если новый объект больше максимального (по [Vehicle.compareTo]), добавляет его в коллекцию.
     * 5. Выводит сообщение о результате операции.
     */
    override fun execute(args: String?) {
        if (cm.baseCollection.isEmpty()) {
            outputManager.println("Коллекция пуста.")
            val newVehicle = vm.setVehicle()
            cm.addVehicle(newVehicle)
            outputManager.println("Объект добавлен в коллекцию.")
            return
        }

        val maxVehicle = cm.baseCollection.maxWithOrNull(compareBy { it.enginePower })
        if (maxVehicle == null) {
            outputManager.println("Ошибка: не удалось определить максимальный элемент.")
            return
        }

        val newVehicle = vm.setVehicle()

        if (newVehicle.compareTo(maxVehicle) > 0) {
            cm.addVehicle(newVehicle)
                outputManager.println("Объект добавлен в коллекцию.")
        } else {
            outputManager.println("Объект не может быть добавлен в коллекцию.")
        }
    }
}