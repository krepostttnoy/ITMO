package utils.inputOutput

import utils.exceptions.InvalidInputException
import java.io.File
import java.io.FileReader
import java.io.InputStream
import java.util.*

class InputManager(private val outputManager: OutputManager) {
    private var scanners:Stack<Scanner> = Stack()
    private var inputStream = System.`in`
    private var scriptMode = false
    private var files:Stack<File> = Stack()

    constructor(
        inputStream: InputStream,
        outputManager: OutputManager
    ): this(outputManager){
        this.inputStream = inputStream
    }

    init{
        scanners.push(Scanner(inputStream))
    }

    fun read(): String{
        return if (scanners.peek().hasNextLine()) {
            scanners.peek().nextLine()
        } else {
            if (scriptMode) {
                finishScriptRead()
                ""
            }else{
                throw InvalidInputException()
            }
        }
    }

    fun startScriptRead(filePath: String){
        val file = File(filePath)
        if (file in files){
            outputManager.println("File already going.")
            throw IllegalStateException("Рекурсия обнаружена: файл ${file.name} уже выполняется.")
        }else{
            outputManager.println("Start executing script from file ${file.name}")
            scanners.push(Scanner(FileReader(file)))
            files.push(file)
            scriptMode = true
            outputManager.disableOutput()
        }
    }

    fun finishScriptRead(){
        scriptMode = false
        scanners.pop()
        outputManager.enableOutput()
        outputManager.println("Script from file was executed")
        files.pop()
    }

}