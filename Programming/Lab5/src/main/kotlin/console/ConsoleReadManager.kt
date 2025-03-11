package console

import baseClasses.FuelType

/**
 * Класс для чтения данных с консоли с валидацией.
 * Реализует интерфейс [IReadManager], предоставляя методы для ввода различных полей с проверкой корректности.
 *
 * @property console Объект для чтения и валидации данных из консоли.
 * @constructor Создаёт экземпляр [ConsoleReadManager] с объектом [ConsoleReadValid] для чтения данных.
 */
class ConsoleReadManager : IReadManager {

    /**
     * Объект для чтения и валидации данных из консоли.
     */
    private val console = ConsoleReadValid()

    /**
     * Запрашивает у пользователя ввод имени транспортного средства.
     * Продолжает запрашивать ввод, пока не будет введено непустое имя.
     *
     * @return Введённое имя транспортного средства.
     */
    override fun readName(): String {
        while (true) {
            print("Введите имя: ")
            val name = console.readLineTrimmed()
            if (name.isNotBlank()) {
                return name
            } else {
                println("Введите корректное имя.")
            }
        }
    }

    /**
     * Запрашивает у пользователя ввод координаты X.
     * Проверяет, что введённое значение больше -818.
     *
     * @return Введённая координата X.
     * @throws NumberFormatException Если введённое значение не является числом.
     * @throws IllegalArgumentException Если введённое значение меньше или равно -818.
     */
    override fun readCoordinateX(): Long {
        while (true) {
            try {
                print("Введите координату X: ")
                val coordinate = console.readLong()
                if (coordinate > -818) {
                    return coordinate
                } else throw IllegalArgumentException("Х должен быть > -818.")
            } catch (e: NumberFormatException) {
                println("Ошибка: Введено некорректное число. Попробуйте ещё раз.")
            } catch (e: IllegalArgumentException) {
                println("Ошибка: ${e.message}")
            }
        }
    }

    /**
     * Запрашивает у пользователя ввод координаты Y.
     * Проверяет, что введённое значение меньше или равно 730.
     *
     * @return Введённая координата Y.
     * @throws NumberFormatException Если введённое значение не является числом.
     * @throws IllegalArgumentException Если введённое значение больше 730.
     */
    override fun readCoordinateY(): Long {
        while (true) {
            try {
                print("Введите координату Y: ")
                val coordinate = console.readLong()
                if (coordinate <= 730) {
                    return coordinate
                } else throw IllegalArgumentException("Y должен быть <= 730.")
            } catch (e: NumberFormatException) {
                println("Ошибка: Введено некорректное число. Попробуйте ещё раз.")
            } catch (e: IllegalArgumentException) {
                println("Ошибка: ${e.message}")
            }
        }
    }

    /**
     * Запрашивает у пользователя ввод мощности двигателя.
     * Проверяет, что введённое значение больше 0.
     *
     * @return Введённая мощность двигателя (может быть null).
     * @throws NumberFormatException Если введённое значение не является числом.
     * @throws IllegalArgumentException Если введённое значение меньше или равно 0.
     */
    override fun readEnginePower(): Float? {
        while (true) {
            try {
                print("Введите мощность двигателя: ")
                val enginePower = console.readFloat()
                if (enginePower != null) {
                    if (enginePower > 0) {
                        return enginePower
                    } else throw IllegalArgumentException("Мощность должна быть > 0.")
                } else throw NumberFormatException("Введено некорректное число.")
            } catch (e: NumberFormatException) {
                println("Ошибка: ${e.message}")
            } catch (e: IllegalArgumentException) {
                println("Ошибка: ${e.message}")
            }
        }
    }

    /**
     * Запрашивает у пользователя ввод вместимости.
     * Проверяет, что введённое значение больше 0.
     *
     * @return Введённая вместимость.
     * @throws NumberFormatException Если введённое значение не является числом.
     * @throws IllegalArgumentException Если введённое значение меньше или равно 0.
     */
    override fun readCapacity(): Float {
        while (true) {
            try {
                print("Введите емкость двигателя: ")
                val capacity = console.readFloat()
                if (capacity != null) {
                    if (capacity > 0) {
                        return capacity
                    } else throw IllegalArgumentException("Емкость должна быть > 0.")
                } else throw NumberFormatException("Введено некорректное число.")
            } catch (e: NumberFormatException) {
                println("Ошибка: ${e.message}")
            } catch (e: IllegalArgumentException) {
                println("Ошибка: ${e.message}")
            }
        }
    }

    /**
     * Запрашивает у пользователя ввод пробега.
     * Проверяет, что введённое значение больше или равно 0.
     *
     * @return Введённый пробег.
     * @throws NumberFormatException Если введённое значение не является числом.
     * @throws IllegalArgumentException Если введённое значение меньше 0.
     */
    override fun readDistanceTravelled(): Int {
        while (true) {
            try {
                print("Введите пробег: ")
                val distanceTravelled = console.readInt()
                if (distanceTravelled != null) {
                    if (distanceTravelled >= 0) {
                        return distanceTravelled
                    } else throw IllegalArgumentException("Пробег должен быть >= 0.")
                } else throw NumberFormatException("Введено некорректное число. Попробуйте ещё раз.")
            } catch (e: NumberFormatException) {
                println("Ошибка: ${e.message}")
            } catch (e: IllegalArgumentException) {
                println("Ошибка: ${e.message}")
            }
        }
    }

    /**
     * Запрашивает у пользователя ввод типа топлива.
     * Проверяет, что введённое значение соответствует одному из значений [FuelType].
     *
     * @return Введённый тип топлива в виде значения [FuelType].
     * @throws IllegalArgumentException Если введённый тип топлива некорректен.
     */
    override fun readFuelType(): FuelType {
        while (true) {
            try {
                print("Введите тип топлива: ")
                val fuelType = console.readLineTrimmed()

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
                println(e.message)
            }
        }
    }
}