package console

import baseClasses.FuelType
import utils.inputOutput.InputManager

/**
 * Интерфейс для чтения данных из консоли или другого источника.
 * Определяет контракт для классов, которые должны предоставлять методы для ввода различных полей объекта [Vehicle].
 */

interface IReadManager {
    fun readName(): String
    fun readCoordinateX(): Long
    fun readCoordinateY(): Long
    fun readEnginePower(): Float?
    fun readCapacity(): Float
    fun readDistanceTravelled(): Int
    fun readFuelType(): FuelType
}