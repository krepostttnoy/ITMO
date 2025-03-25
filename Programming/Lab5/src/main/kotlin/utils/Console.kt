package utils

import baseClasses.ExitFlag
import collection.CollectionManager
import commands.ConsoleCommandExecutor
import file.ConsoleFileManager
import utils.inputOutput.InputManager
import utils.inputOutput.OutputManager

class Console{
    private val outputManager = OutputManager()
    private val inputManager = InputManager(outputManager)
    private val collectionManager = CollectionManager()
    private val fileManager = ConsoleFileManager(collectionManager, outputManager, inputManager)
    private val commandExecutor = ConsoleCommandExecutor(collectionManager, fileManager, inputManager, outputManager)

    fun startInteractiveMode(){
        try {
            while (!ExitFlag.exitFlag) {
                print("$ ")
                val input = readLine()?.trim()
                commandExecutor.executeCommand(input!!.lowercase())
            }
        } catch (e: IllegalArgumentException) {
            println("${e.message}")
        } catch (e: StackOverflowError) {
            println("${e.message}")
        } catch (e: Exception){
            println("Unknown error: ${e.message}")
        }
    }
}