package commands

import collection.CollectionManager
import console.IVehicleManager
import utils.inputOutput.InputManager
import utils.inputOutput.OutputManager

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
    private val vm: IVehicleManager,
    private val outputManager: OutputManager,
    private val inputManager: InputManager
) : Command {

    override val interactive = true

    /**
     * Выполняет команду добавления нового транспортного средства.
     * Создаёт новый объект [Vehicle] с помощью [vm] и добавляет его в коллекцию через [cm].
     * Выводит сообщение об успешном добавлении или об ошибке.
     *
     * @throws IllegalArgumentException Если создание или добавление объекта [Vehicle] не удалось.
     */
    override fun execute(args: String?) {

        try {
            val newVehicle = vm.setVehicle()
            cm.addVehicle(newVehicle)
            outputManager.println("Object was added.")
        } catch (e: Error) {
            println("Object can't be added.")
        }
    }
}