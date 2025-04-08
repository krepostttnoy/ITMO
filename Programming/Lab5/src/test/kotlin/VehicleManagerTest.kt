import baseClasses.Coordinates
import baseClasses.FuelType
import console.IReadManager
import console.ConsoleVehicleManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class VehicleManagerTest {
    private lateinit var vm: ConsoleVehicleManager
    private lateinit var rm: IReadManager

    @BeforeEach
    fun setUp() {
        rm = mock()
        vm = ConsoleVehicleManager(rm)
    }

    @Test
    fun `setVehicle should set new vehicle with valid input`() {

        Mockito.`when`(rm.readName()).thenReturn("Car1")
        Mockito.`when`(rm.readCoordinateX()).thenReturn(10L)
        Mockito.`when`(rm.readCoordinateY()).thenReturn(20L)
        Mockito.`when`(rm.readEnginePower()).thenReturn(100.0f)
        Mockito.`when`(rm.readCapacity()).thenReturn(50.0f)
        Mockito.`when`(rm.readDistanceTravelled()).thenReturn(1000)
        Mockito.`when`(rm.readFuelType()).thenReturn(FuelType.DIESEL)

        val vehicle = vm.setVehicle()

        assertNotNull(vehicle, "Vehicle should not be null")
        assertEquals("Car1", vehicle.name)
        assertEquals(Coordinates(10L, 20L), vehicle.coordinates)
        assertEquals(100.0f, vehicle.enginePower)
        assertEquals(50.0f, vehicle.capacity)
        assertEquals(1000, vehicle.distanceTravelled)
        assertEquals(FuelType.DIESEL, vehicle.fuelType)
    }

    @Test
    fun `setVehicle should set vehicle with null enginePower and fuelType`() {
        Mockito.`when`(rm.readName()).thenReturn("Car2")
        Mockito.`when`(rm.readCoordinateX()).thenReturn(30L)
        Mockito.`when`(rm.readCoordinateY()).thenReturn(40L)
        Mockito.`when`(rm.readEnginePower()).thenReturn(null)
        Mockito.`when`(rm.readCapacity()).thenReturn(60.0f)
        Mockito.`when`(rm.readDistanceTravelled()).thenReturn(2000)
        Mockito.`when`(rm.readFuelType()).thenReturn(null)

        val vehicle = vm.setVehicle()

        assertNotNull(vehicle, "Vehicle should not be null")
        assertEquals("Car2", vehicle.name)
        assertEquals(Coordinates(30L, 40L), vehicle.coordinates)
        assertEquals(null, vehicle.enginePower)
        assertEquals(60.0f, vehicle.capacity)
        assertEquals(2000, vehicle.distanceTravelled)
        assertEquals(null, vehicle.fuelType)
    }
}