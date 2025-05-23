package commands

import baseClasses.Vehicle
import collection.CollectionManager
import utils.inputOutput.OutputManager

/**
 * Команда для очистки коллекции транспортных средств.
 * Удаляет все элементы из коллекции, управляемой [CollectionManager], и очищает список использованных идентификаторов в [Vehicle].
 *
 * @property cm Менеджер коллекции, который будет очищен.
 * @constructor Создаёт команду [ClearCommand] с заданным менеджером [cm].
 */
class ClearCommand(
    private val cm: CollectionManager,
    private val outputManager: OutputManager
    ) : Command {
    override val interactive = false

    /**
     * Выполняет команду очистки коллекции.
     *
     * Алгоритм:
     * 1. Если коллекция пуста, выводит сообщение и завершает выполнение.
     * 2. Удаляет все элементы из коллекции с помощью [CollectionManager].
     * 3. Очищает список использованных идентификаторов в [Vehicle.existingIds].
     * 4. Выводит сообщение об успешной очистке и текущий размер коллекции.
     */
    override fun execute(args: String?) {
        if (cm.baseCollection.isEmpty()) {
            outputManager.println("Коллекция пуста.")
            return
        }

        cm.baseCollection.clear()
        Vehicle.existingIds.clear()
            outputManager.println("Все элементы удалены. Размер коллекции: ${cm.baseCollection.size}")
    }
}