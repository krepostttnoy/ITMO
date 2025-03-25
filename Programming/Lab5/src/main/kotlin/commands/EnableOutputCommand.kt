package commands

import utils.inputOutput.OutputManager

class EnableOutputCommand(private val outputManager: OutputManager): Command {
    override fun execute() {
        outputManager.enableOutput()
    }
}