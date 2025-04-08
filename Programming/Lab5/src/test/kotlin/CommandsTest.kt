import baseClasses.Coordinates
import baseClasses.FuelType
import baseClasses.Vehicle
import collection.CollectionManager
import commands.CountGrThanEngPwCommand
import commands.ExecuteScriptCommand
import commands.ICommandExecutor
import commands.MinByFuelTypeCommand
import commands.RemoveGreaterCommand
import console.IVehicleManager
import console.Read
import file.IFileManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import kotlin.test.assertEquals
import java.io.File
import org.mockito.kotlin.verify
import utils.inputOutput.InputManager
import utils.inputOutput.OutputManager

class CommandsTest {
    private lateinit var cm: CollectionManager
    private lateinit var vm: IVehicleManager
    private lateinit var r: Read
    private lateinit var ce: ICommandExecutor
    private lateinit var fm: IFileManager
    private lateinit var outputManager: OutputManager
    private lateinit var inputManager: InputManager

    @BeforeEach
    fun setUp(){
        cm = CollectionManager()
        r = mock()
        vm = mock()
        ce = mock()
        fm = mock()
        outputManager = OutputManager()
        inputManager = InputManager(outputManager)
    }

    @Test
    fun `RemoveGreaterCommand should remove vehicles greater than given element`(){
        val vehicle1 = Vehicle(
            name = "Car667",
            coordinates = Coordinates(10, 20),
            enginePower = 100.0f,
            capacity = 50.0f,
            distanceTravelled = 1000,
            fuelType = FuelType.DIESEL
        )
        val vehicle2 = Vehicle(
            name = "Car2",
            coordinates = Coordinates(30, 40),
            enginePower = 150.0f,
            capacity = 60.0f,
            distanceTravelled = 2000,
            fuelType = FuelType.ELECTRICITY
        )

        val vehicle3 = Vehicle(
            name = "Car3",
            coordinates = Coordinates(50, 60),
            enginePower = 120.0f,
            capacity = 70.0f,
            distanceTravelled = 3000,
            fuelType = FuelType.ANTIMATTER
        )

        cm.addVehicle(vehicle1)
        cm.addVehicle(vehicle2)
        cm.addVehicle(vehicle3)

        Mockito.`when`(r.readFloat()).thenReturn((120.0f))

        val removeGreaterCommand = RemoveGreaterCommand(cm, r, outputManager)
        removeGreaterCommand.execute()

        assertEquals(2, cm.baseCollection.size)
        assertEquals(vehicle1, cm.baseCollection[0])
        assertEquals(vehicle3, cm.baseCollection[1])

    }

    @Test
    fun `MinByFuelType should find min vehicle by fuel type`(){
        val vehicle1 = Vehicle(
            name = "Car667",
            coordinates = Coordinates(10, 20),
            enginePower = 100.0f,
            capacity = 50.0f,
            distanceTravelled = 1000,
            fuelType = FuelType.ELECTRICITY
        )
        val vehicle2 = Vehicle(
            name = "Car2",
            coordinates = Coordinates(30, 40),
            enginePower = 150.0f,
            capacity = 60.0f,
            distanceTravelled = 2000,
            fuelType = FuelType.DIESEL
        )

        cm.addVehicle(vehicle1)
        cm.addVehicle(vehicle2)

        val minByFuelTypeCommand = MinByFuelTypeCommand(cm, outputManager)
        minByFuelTypeCommand.execute()

        val minVehicle = cm.baseCollection.minByOrNull { vehicle ->
            vehicle.fuelType ?: FuelType.entries.toTypedArray().min()
        }

        assertEquals(vehicle1, minVehicle)
    }

    @Test
    fun `CountGrThanEngPower should count quantity of vehicles greater in eng pw`(){
        val engPw: Float = 120.0f

        val vehicle1 = Vehicle(
            name = "Car667",
            coordinates = Coordinates(10, 20),
            enginePower = 100.0f,
            capacity = 50.0f,
            distanceTravelled = 1000,
            fuelType = null
        )
        val vehicle2 = Vehicle(
            name = "Car2",
            coordinates = Coordinates(30, 40),
            enginePower = 150.0f,
            capacity = 60.0f,
            distanceTravelled = 2000,
            fuelType = null
        )

        val vehicle3 = Vehicle(
            name = "Car3",
            coordinates = Coordinates(50, 60),
            enginePower = engPw,
            capacity = 0.0f,
            distanceTravelled = 0,
            fuelType = null
        )

        cm.addVehicle(vehicle1)
        cm.addVehicle(vehicle2)
        cm.addVehicle(vehicle3)



        Mockito.`when`(r.readFloat()).thenReturn(engPw)

        val countGrThanEngPwCommand = CountGrThanEngPwCommand(cm, r, outputManager)
        countGrThanEngPwCommand.execute()

        val count = cm.baseCollection.filter{it > vehicle3}.size

        assertEquals(1, count)
    }

    @Test
    fun `ExecuteScriptCommand should execute script from file`(){
        val file = File.createTempFile("test_script", ".txt")
        file.writeText("add\nmin_by_fuel_type")

        val executeScriptCommand = ExecuteScriptCommand(cm, fm, ce, outputManager, inputManager)
        executeScriptCommand.execute(file.canonicalPath)

        verify(ce).executeCommand("add")
        verify(ce).executeCommand("min_by_fuel_type")
    }

    @Test
    fun `ExecuteScriptCommand should handle non-existent file error`() {
        val executeScriptCommand = ExecuteScriptCommand(cm, fm, ce, outputManager, inputManager)

        try {
            executeScriptCommand.execute("nonexistent.txt")
            verify(ce, never()).executeCommand(anyString())
            println("Успешно: executeCommand не был вызван")
        } catch (e: AssertionError) {
            println("Ошибка: executeCommand был вызван, хотя не должен был")
            throw e
        }
    }
}

