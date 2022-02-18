package duke.instruction;

import duke.task.TaskManager;
import duke.ui.Ui;

final class FindInstruction extends Instruction {

    private static final String HELP_MESSAGE = "find: find a list of tasks that contain the given"
            + "keyword.\n"
            + "usage: find <keyword>";

    private final String keyword;


    /**
     * Constructs a Find instruction.
     *
     * @param instruction The raw instruction from user.
     * @param tasks The task manager to be used.
     * @throws InvalidInstructionException If the instruction is not valid.
     */
    FindInstruction(String instruction, TaskManager tasks) throws InvalidInstructionException {

        super("find", tasks);
        this.keyword = parseInstruction(instruction);

    }

    private static String parseInstruction(String instruction) throws InvalidInstructionException {

        String keyword;
        String[] words = instruction.split(" ", 2);
        if (words.length != 2) {
            throw new InvalidInstructionException("Please provide at least a keyword for searching!");
        }
        return words[1];
    }

    /**
     * Searches for tasks that contain the keyword, and prints the result out.
     *
     * @param ui The UI to be used by this instruction.
     */
    @Override
    public void act(Ui ui) {

        ui.printMessage(TaskManager.listToString(tasks.search(keyword)));
    }

    /**
     * Performs the instruction and returns the output message to be printed to the GUI.
     * This method is written for GUI only. If the input is supplied to GUI,the output
     * will not be printed to the output stream.
     *
     * @return The output message to the GUI.
     */
    public String getOutputMessage() {

        return TaskManager.listToString((tasks.search(keyword)));
    }

    /**
     * Get the help message for the current instruction.
     *
     * @return The help message.
     */
    protected static String getHelpMessage() {
        return HELP_MESSAGE;
    }
}
