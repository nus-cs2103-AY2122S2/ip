package duke.instruction;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import duke.main.DukeException;
import duke.ui.Ui;


public final class HelpInstruction extends Instruction {

    private static final String HELP_MESSAGE = "help: displays the help page of the application\n"
            + "usage: help";

    private static class HelpPage {

        private static final String START_MESSAGE = "Here is the usage list of commands:";
        private static final String END_MESSAGE = "Let's try them out!";

        private String helpPage;

        private HelpPage(List<Class<? extends Instruction>> instructionTypes) throws DukeException {

            StringBuilder helpMessage = new StringBuilder();
            helpMessage.append(START_MESSAGE);
            helpMessage.append("\n");

            for (Class<? extends Instruction> i : instructionTypes) {
                try {
                    helpMessage.append((String)
                            i.getDeclaredMethod("getHelpMessage").invoke(null));
                    helpMessage.append("\n");
                } catch (NoSuchMethodException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException e) {
                    throw new IncompleteInstructionImplementationException(
                            "The getHelpMessage method is not implemented properly in "
                                    + i.getName() + ", try re-download the application."
                    );
                }
            }

            helpMessage.append("\n");
            helpMessage.append(END_MESSAGE);
            helpPage = helpMessage.toString();
        }
    }
    /**
     * Constructs a "help" instruction.
     */
    public HelpInstruction() {
        super("help");
    }

    /**
     * Performs the associated action of the task. By default, there is no action associated to a task.
     *
     * @param ui The UI to be used by this instruction.
     */
    @Override
    public void act(Ui ui) throws DukeException {
        ui.printMessage(getOutputMessage());
    }

    /**
     * Performs the instruction and returns the output message to be printed to the GUI.
     * This method is written for GUI only. If the input is supplied to GUI,the output
     * will not be printed to the output stream.
     *
     * @return The output message to the GUI.
     */
    @Override
    public String getOutputMessage() throws DukeException {
        return new HelpPage(Instruction.getInstructionTypes()).helpPage;
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
