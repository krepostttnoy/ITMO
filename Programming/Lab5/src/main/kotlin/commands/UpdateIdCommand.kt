package commands

import baseClasses.Coordinates
import baseClasses.Vehicle
import collection.CollectionManager
import console.ConsoleReadValid
import console.ConsoleReadManager

/**
 * Команда для обновления данных транспортного средства в коллекции по его идентификатору.
 * Использует [CollectionManager] для управления коллекцией, [ConsoleReadValid] для чтения данных от пользователя
 * и [ConsoleReadManager] для ввода новых значений полей.
 *
 * @property cm Менеджер коллекции, содержащий список транспортных средств.
 * @property console Объект для чтения и валидации данных из консоли.
 * @constructor Создаёт команду [UpdateIdCommand] с заданным менеджером [cm].
 */
class UpdateIdCommand(private val cm: CollectionManager) : Command {

    /**
     * Объект для чтения и валидации данных из консоли.
     */
    val console = ConsoleReadValid()

    /**
     * Выполняет команду обновления данных транспортного средства по указанному идентификатору.
     *
     * Алгоритм:
     * 1. Если коллекция пуста, выводит сообщение и завершает выполнение.
     * 2. Выводит список доступных элементов с их ID и именами.
     * 3. Запрашивает у пользователя ID элемента для обновления.
     * 4. Если ID некорректен или элемент не найден, выводит сообщение об ошибке.
     * 5. Запрашивает у пользователя поле для обновления (name, coordinates, enginePower, capacity, distanceTravelled, fuelType).
     * 6. Обновляет указанное поле с помощью [ConsoleReadManager], создавая копию объекта [Vehicle] с новым значением.
     * 7. Заменяет старый элемент в коллекции на обновлённый.
     */
    override fun execute() {
        if (cm.baseCollection.isNotEmpty()) {
            cm.baseCollection.forEach { vehicle ->
                println("Список доступных ID: ${vehicle.id}, name - ${vehicle.name}")
            }
            println("Введите ID элемента для обновления: ")
            val id = console.readInt() ?: return println("Неверный ID. Повторите попытку.")
            val index = cm.baseCollection.indexOfFirst { it.id == id }
            if (index == -1) {
                println("Элемента с ID = $id не существует.")
                return
            }

            print("Какое поле обновить? (name, coordinates, enginePower, capacity, distanceTravelled, fuelType): ")
            val field = console.readLineTrimmed()
            val oldVehicle = cm.baseCollection[index]
            Vehicle.removeId(oldVehicle.id)
            val rm = ConsoleReadManager()
            val newVehicle = when (field) {
                "name" -> {
                    val newName = rm.readName()
                    oldVehicle.copy(name = newName)
                }
                "coordinates", "coords" -> {
                    val newCoordX = rm.readCoordinateX()
                    val newCoordY = rm.readCoordinateY()
                    val newCoordinates = Coordinates(newCoordX, newCoordY)
                    oldVehicle.copy(coordinates = newCoordinates)
                }
                "enginePower", "ep" -> {
                    val newEnginePower = rm.readEnginePower()
                    oldVehicle.copy(enginePower = newEnginePower)
                }
                "capacity", "cap" -> {
                    val newCapacity = rm.readCapacity()
                    oldVehicle.copy(capacity = newCapacity)
                }
                "distanceTravelled", "dt" -> {
                    val newDistanceTravelled = rm.readDistanceTravelled()
                    oldVehicle.copy(distanceTravelled = newDistanceTravelled)
                }
                "fuelType", "ft" -> {
                    val newFuelType = rm.readFuelType()
                    oldVehicle.copy(fuelType = newFuelType)
                }
                else -> {
                    println("Неверное поле. Доступные поля: name, coordinates, enginePower, capacity, distanceTravelled, fuelType")
                    oldVehicle
                }
            }

            cm.baseCollection[index] = newVehicle
        } else {
            println("Коллекция пуста.\nПеред вызовом этой функции заполните коллекцию.")
        }
    }
}