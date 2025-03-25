package console

import baseClasses.FuelType
import utils.inputOutput.InputManager
import utils.inputOutput.OutputManager

/**
 * Класс для чтения и валидации данных из консоли.
 * Реализует интерфейс [Read], предоставляя методы для чтения строк, целых чисел, чисел с плавающей точкой и длинных целых чисел.
 *
 * @constructor Создаёт экземпляр [ConsoleReadValid] для чтения данных из консоли.
 */
class ConsoleReadValid(
    private val outputManager: OutputManager,
    private val inputManager: InputManager
) : Read {

    /**
     * Читает строку из консоли и преобразует её в целое число.
     *
     * @return Целое число, если преобразование успешно, или null, если строка не является числом.
     */
    override fun readInt(): Int? {
        return readLineTrimmed().toIntOrNull()
    }

    /**
     * Читает строку из консоли и преобразует её в число с плавающей точкой.
     *
     * @return Число с плавающей точкой, если преобразование успешно, или null, если строка не является числом.
     */
    override fun readFloat(): Float? {
        return readLineTrimmed().toFloatOrNull()
    }

    /**
     * Читает строку из консоли и удаляет начальные и конечные пробелы.
     *
     * @return Обработанная строка или пустая строка, если ввод равен null.
     */
    override fun readLineTrimmed(): String {
        return inputManager.read().trim()
    }

    /**
     * Читает строку из консоли и преобразует её в длинное целое число.
     *
     * @return Длинное целое число.
     * @throws NumberFormatException Если строка не является числом.
     */
    override fun readLong(): Long {
        return readLineTrimmed().toLong()
    }

    fun validName(): String{
        while(true){
            try{
                outputManager.print("Введите имя: ")
                val name = readLineTrimmed()
                if (name.isNotBlank()) {
                    return name
                } else {
                    throw IllegalArgumentException()
                }
                return name
            } catch (e: IllegalArgumentException) {
                outputManager.println("Ошибка: ${e.message}")
            }

        }
    }

    fun validReadCoordinateX(): Long {
        while (true) {
            try {
                outputManager.print("Введите координату X: ")
                val coordinate = readLong()
                if (coordinate > -818) {
                    return coordinate
                } else throw IllegalArgumentException("Х должен быть > -818.")
            } catch (e: NumberFormatException) {
                outputManager.println("Ошибка: Введено некорректное число. Попробуйте ещё раз.")
            } catch (e: IllegalArgumentException) {
                outputManager.println("Ошибка: ${e.message}")
            }
        }
    }

    fun validReadCoordinateY(): Long {
        while (true) {
            try {
                outputManager.print("Введите координату Y: ")
                val coordinate = readLong()
                if (coordinate <= 730) {
                    return coordinate
                } else throw IllegalArgumentException("Y должен быть <= 730.")
            } catch (e: NumberFormatException) {
                outputManager.println("Ошибка: Введено некорректное число. Попробуйте ещё раз.")
            } catch (e: IllegalArgumentException) {
                outputManager.println("Ошибка: ${e.message}")
            }
        }
    }

    fun validReadEnginePower(): Float {
        while (true) {
            try {
                outputManager.print("Введите мощность двигателя: ")
                val enginePower = readFloat()
                if (enginePower != null) {
                    if (enginePower > 0) {
                        return enginePower
                    } else throw IllegalArgumentException("Мощность должна быть > 0.")
                } else throw NumberFormatException("Введено некорректное число.")
            } catch (e: NumberFormatException) {
                outputManager.println("Ошибка: ${e.message}")
            } catch (e: IllegalArgumentException) {
                outputManager.println("Ошибка: ${e.message}")
            }
        }
    }

    fun validReadCapacity(): Float {
        while (true) {
            try {
                outputManager.print("Введите емкость двигателя: ")
                val capacity = readFloat()
                if (capacity != null) {
                    if (capacity > 0) {
                        return capacity
                    } else throw IllegalArgumentException("Емкость должна быть > 0.")
                } else throw NumberFormatException("Введено некорректное число.")
            } catch (e: NumberFormatException) {
                outputManager.println("Ошибка: ${e.message}")
            } catch (e: IllegalArgumentException) {
                outputManager.println("Ошибка: ${e.message}")
            }
        }
    }

    fun validReadDistanceTravelled(): Int {
        while (true) {
            try {
                outputManager.print("Введите пробег: ")
                val distanceTravelled = readInt()
                if (distanceTravelled != null) {
                    if (distanceTravelled >= 0) {
                        return distanceTravelled
                    } else throw IllegalArgumentException("Пробег должен быть >= 0.")
                } else throw NumberFormatException("Введено некорректное число. Попробуйте ещё раз.")
            } catch (e: NumberFormatException) {
                outputManager.println("Ошибка: ${e.message}")
            } catch (e: IllegalArgumentException) {
                outputManager.println("Ошибка: ${e.message}")
            }
        }
    }

    fun validReadFuelType(): FuelType {
        while (true) {
            try {
                outputManager.print("Введите тип топлива: ")
                val fuelType = readLineTrimmed()

                return when (fuelType.lowercase()) {
                    "электричество", "электро" -> FuelType.ELECTRICITY
                    "анти", "антиматерия" -> FuelType.ANTIMATTER
                    "дизель", "диз" -> FuelType.DIESEL
                    else -> throw IllegalArgumentException(
                        "Ошибка: некорректный тип топлива!\n" +
                                "Возможные типы топлива: ${FuelType.entries.joinToString(", ") { it.description }}"
                    )
                }
            } catch (e: IllegalArgumentException) {
                outputManager.println(e.message.toString())
            }
        }
    }
}


