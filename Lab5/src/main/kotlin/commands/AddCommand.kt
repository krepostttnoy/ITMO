package commands

import collection.CollectionManager
import console.IVehicleManager

/**
 * Команда для добавления нового транспортного средства в коллекцию.
 * Использует [IVehicleManager] для создания нового объекта [Vehicle] и добавляет его в коллекцию через [CollectionManager].
 *
 * @property cm Менеджер коллекции, в которую будет добавлено транспортное средство.
 * @property vm Менеджер для создания нового транспортного средства.
 * @constructor Создаёт команду [AddCommand] с заданными менеджерами [cm] и [vm].
 */
class AddCommand(
    private val cm: CollectionManager,
    private val vm: IVehicleManager
) : Command {

    /**
     * Выполняет команду добавления нового транспортного средства.
     * Создаёт новый объект [Vehicle] с помощью [vm] и добавляет его в коллекцию через [cm].
     * Выводит сообщение об успешном добавлении или об ошибке.
     *
     * @throws IllegalArgumentException Если создание или добавление объекта [Vehicle] не удалось.
     */
    override fun execute() {
        try {
            val newVehicle = vm.setVehicle()
            cm.addVehicle(newVehicle)
            println("Object was added.")
        } catch (e: IllegalArgumentException) {
            println("Object can't be added.")
        }
    }
}