package commands

import baseClasses.Coordinates
import baseClasses.Vehicle
import collection.CollectionManager
import console.Read
import utils.inputOutput.OutputManager
import kotlin.text.toFloatOrNull

/**
 * Команда для подсчёта количества транспортных средств с мощностью двигателя больше заданного значения.
 * Использует [CollectionManager] для доступа к коллекции и [Read] для чтения данных от пользователя.
 * Сравнение выполняется с помощью метода [Vehicle.compareTo].
 *
 * @property cm Менеджер коллекции, содержащий список транспортных средств.
 * @property rm Объект для чтения данных от пользователя.
 * @constructor Создаёт команду [CountGrThanEngPwCommand] с заданными менеджерами [cm] и [rm].
 */
class CountGrThanEngPwCommand(
    private val cm: CollectionManager,
    private val rm: Read,
    private val outputManager: OutputManager
) : Command {
    override val interactive = true

    /**
     * Выполняет команду подсчёта количества объектов с мощностью двигателя больше заданного значения.
     *
     * Алгоритм:
     * 1. Если коллекция пуста, выводит сообщение и завершает выполнение.
     * 2. Получает значение мощности двигателя из аргумента [enginePowerStr] или запрашивает его у пользователя через [rm].
     * 3. Создаёт временный объект [Vehicle] с указанной мощностью для сравнения.
     * 4. Подсчитывает количество объектов в коллекции, у которых мощность больше заданной.
     * 5. Выводит результат.
     *
     * @param enginePowerStr Строковое представление мощности двигателя для сравнения (может быть null).
     */
    override fun execute(enginePowerStr: String?) {
        if (cm.baseCollection.isEmpty()) {
            outputManager.println("Коллекция пуста.")
            return
        }

        val engPower = enginePowerStr?.toFloatOrNull() ?: run {
            outputManager.print("Введите enginePower для сравнения: ")
            rm.readFloat() ?: return
        }

        val element = Vehicle(
            name = "ComparingModel",
            coordinates = Coordinates(0, 0),
            enginePower = engPower,
            capacity = 0.0f,
            distanceTravelled = 0,
            fuelType = null
        )

        val count = cm.baseCollection.filter { it > element }.size

            outputManager.println("Кол-во объектов enginePower которых больше $engPower -> $count")
    }

    /**
     * Выполняет команду без аргументов.
     * Вызывает [execute] с параметром [enginePowerStr] равным null,
     * что приводит к запросу значения у пользователя.
     */

}