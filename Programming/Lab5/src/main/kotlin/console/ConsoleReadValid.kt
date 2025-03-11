package console

/**
 * Класс для чтения и валидации данных из консоли.
 * Реализует интерфейс [Read], предоставляя методы для чтения строк, целых чисел, чисел с плавающей точкой и длинных целых чисел.
 *
 * @constructor Создаёт экземпляр [ConsoleReadValid] для чтения данных из консоли.
 */
class ConsoleReadValid : Read {

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
        return readLine()?.trim() ?: ""
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
}


