package commands

import baseClasses.Vehicle
import collection.CollectionManager
import console.ConsoleReadValid
import utils.inputOutput.InputManager
import utils.inputOutput.OutputManager

/**
 * Команда для удаления транспортного средства из коллекции по его идентификатору.
 * Использует [CollectionManager] для управления коллекцией и [ConsoleReadValid] для чтения данных от пользователя.
 *
 * @property cm Менеджер коллекции, содержащий список транспортных средств.
 * @constructor Создаёт команду [RemoveByIdCommand] с заданным менеджером [cm].
 */
class RemoveByIdCommand(
    private val cm: CollectionManager,
    private val outputManager: OutputManager,
    private val inputManager: InputManager
    ) : Command {

    /**
     * Выполняет команду удаления транспортного средства по указанному идентификатору.
     *
     * Алгоритм:
     * 1. Если коллекция пуста, выводит сообщение и завершает выполнение.
     * 2. Если [idStr] не указано, выводит список текущих элементов с их ID.
     * 3. Получает идентификатор из аргумента [idStr] или запрашивает его у пользователя через [ConsoleReadValid].
     * 4. Находит элемент с указанным ID в коллекции.
     * 5. Если элемент найден, удаляет его из коллекции и из списка использованных ID в [Vehicle].
     * 6. Выводит сообщение об успешном удалении или об ошибке, если элемент не найден.
     *
     * @param idStr Строковое представление идентификатора элемента для удаления (может быть null).
     */
    fun execute(idStr: String?) {
        val console = ConsoleReadValid(outputManager, inputManager)

        if (cm.baseCollection.isEmpty()) {
            outputManager.println("Коллекция пуста. Перед удалением добавьте элементы с помощью команды 'add'.")
            return
        }

        if (idStr == null) {
            outputManager.println("Текущие элементы:")
            cm.baseCollection.forEach { vehicle ->
                outputManager.println("ID: ${vehicle.id}")
            }
        }

        val id: Int? = if (idStr == null) {
            outputManager.print("Введите ID элемента, который хотите удалить: ")
            console.readInt()
        } else {
            idStr.toIntOrNull()
        }

        val index = cm.baseCollection.indexOfFirst { it.id == id }
        if (index == -1) {
            outputManager.println("Элемента с ID = $id не существует.")
            return
        }

        val vehicleToRemove = cm.baseCollection[index]
        cm.baseCollection.removeAt(index)
        Vehicle.removeId(vehicleToRemove.id)

        outputManager.println("Элемент удален.")
    }

    /**
     * Выполняет команду без аргументов.
     * Вызывает [execute] с параметром [idStr] равным null,
     * что приводит к запросу идентификатора у пользователя.
     */
    override fun execute() {
        execute(null)
    }
}