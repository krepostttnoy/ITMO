package console

import baseClasses.Vehicle

/**
 * Интерфейс для управления созданием объектов [Vehicle].
 * Определяет контракт для классов, которые должны предоставлять метод для создания транспортного средства.
 */
interface IVehicleManager {
    fun setVehicle(): Vehicle
}