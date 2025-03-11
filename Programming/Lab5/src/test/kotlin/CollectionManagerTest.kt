import baseClasses.Coordinates
import baseClasses.FuelType
import baseClasses.Vehicle
import collection.CollectionManager
import kotlin.test.Test
import kotlin.test.assertEquals

class CollectionManagerTest {
    private val cm = CollectionManager()

    @Test
    fun `addVehicle must add vehicle`(){
        val vehicle = Vehicle(
            name = "Car667",
            coordinates = Coordinates(3, 4),
            enginePower = 0.0f,
            capacity = 0.0f,
            distanceTravelled = 0,
            fuelType = FuelType.ANTIMATTER
        )

        cm.addVehicle(vehicle)
        assertEquals(1, cm.baseCollection.size)
        assertEquals(vehicle, cm.baseCollection[0])
    }

    @Test
    fun `getCollection must return collection`(){
        val vehicle = Vehicle(
            name = "Car667",
            coordinates = Coordinates(3, 4),
            enginePower = 0.0f,
            capacity = 0.0f,
            distanceTravelled = 0,
            fuelType = FuelType.ANTIMATTER
        )

        cm.addVehicle(vehicle)
        cm.getCollection()
        assertEquals(1, cm.getCollection().size)
        assertEquals(vehicle, cm.getCollection()[0])
    }

    @Test
    fun `sortCollection must sort by enginePower`(){
        val vehicle1 = Vehicle(
            name = "Car667",
            coordinates = Coordinates(3, 4),
            enginePower = 50.0f,
            capacity = 0.0f,
            distanceTravelled = 0,
            fuelType = FuelType.ANTIMATTER
        )

        val vehicle2 = Vehicle(
            name = "Car667",
            coordinates = Coordinates(3, 4),
            enginePower = 100.0f,
            capacity = 0.0f,
            distanceTravelled = 0,
            fuelType = FuelType.ANTIMATTER
        )

        cm.addVehicle(vehicle1)
        cm.addVehicle(vehicle2)
        cm.sortCollectionByEnginePower()
        assertEquals(vehicle1, cm.baseCollection[0])
        assertEquals(vehicle2, cm.baseCollection[1])
    }
}