package duke.instruction;

import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents the instruction 'quit'.
 */
final class QuitInstruction extends Instruction {

    private static final String HELP_MESSAGE = "bye: quit the application.\n"
            + "usage: bye";

    private static final String SEE_YOU_MESSAGE = "Bye!";

    /**
     * Constructor, sets the description of the instruction.
     *
     * @param message The terminating command that signals the end of program.
     */
    QuitInstruction(String message, TaskManager tasks) {
        super(message, tasks);
    }

    /**
     * Returns a see-you message.
     *
     * @return The see-you message.
     */
    @Override
    public void act(Ui ui) {
        ui.printMessage(QuitInstruction.SEE_YOU_MESSAGE);
    }

    /**
     * Performs the instruction and returns the output message to be printed to the GUI.
     * This method is written for GUI only. If the input is supplied to GUI,the output
     * will not be printed to the output stream.
     *
     * @return The output message to the GUI.
     */
    public String getOutputMessage() {

        return SEE_YOU_MESSAGE;
    }

    /**
     * Get the help message for the current instruction.
     *
     * @return The help message.
     */
    static String getHelpMessage() {
        return HELP_MESSAGE;
    }
}
