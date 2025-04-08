package commands

import utils.inputOutput.OutputManager

class DisableOutputCommand(private val outputManager: OutputManager): Command {
    override val interactive = false

    override fun execute(args: String?) {
        outputManager.disableOutput()
    }
}