package commands

import collection.CollectionManager
import utils.inputOutput.OutputManager

/**
 * Команда для вычисления среднего значения мощности двигателя транспортных средств в коллекции.
 * Использует [CollectionManager] для доступа к коллекции и вычисляет среднее значение
 * свойства [Vehicle.enginePower] для всех элементов.
 *
 * @property cm Менеджер коллекции, содержащий список транспортных средств.
 * @constructor Создаёт команду [AvgOfEnginePowerCommand] с заданным менеджером [cm].
 */
class AvgOfEnginePowerCommand(
    private val cm: CollectionManager,
    private val outputManager: OutputManager
    ) : Command {
    override val interactive = false

    /**
     * Выполняет команду вычисления среднего значения мощности двигателя.
     *
     * Алгоритм:
     * 1. Если коллекция пуста, выводит сообщение и завершает выполнение.
     * 2. Вычисляет сумму всех значений [Vehicle.enginePower], заменяя null на 0.0.
     * 3. Делит сумму на количество элементов в коллекции.
     * 4. Выводит среднее значение, сумму и размер коллекции.
     */
    override fun execute(args: String?) {
        if (cm.baseCollection.isEmpty()) {
            outputManager.println("Коллекция пуста.")
            return
        }

        val size = cm.baseCollection.size
        val sum = cm.baseCollection.sumOf { vehicle ->
            (vehicle.enginePower ?: 0.0f).toDouble()
        }

            outputManager.println("Avg: ${sum/size}. Sum -> $sum, size -> $size")
    }
}