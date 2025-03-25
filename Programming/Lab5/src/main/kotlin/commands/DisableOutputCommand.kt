package commands

import utils.inputOutput.OutputManager

class DisableOutputCommand(private val outputManager: OutputManager): Command {
    override fun execute() {
        outputManager.disableOutput()
    }
}