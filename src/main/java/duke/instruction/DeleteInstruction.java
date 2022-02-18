package duke.instruction;

import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

final class DeleteInstruction extends Instruction {

    private static final String HELP_MESSAGE = "delete: delete a task.\n"
            + "usage: delete <task_id>";

    private final int toDeleteIndex;
    private final Task toDelete;

    /**
     * Instantiates an instruction "delete", with the given line of instruction.
     *
     * @param instruction The line of instruction for deletion, starting with 'delete'.
     * @param tasks The task manager used.
     * @throws InvalidInstructionException If the given instruction does not contain a valid index.
     */
    DeleteInstruction(String instruction, TaskManager tasks) throws InvalidInstructionException {
        this(parseInstruction(instruction, tasks), tasks);
    }

    /**
     * A helper constructor that helps to construct a delete instruction.
     * @param index The index of the task to be deleted.
     * @param tasks The task manager to be used.
     */
    private DeleteInstruction(int index, TaskManager tasks) {
        super("delete", tasks);
        this.toDeleteIndex = index;
        this.toDelete = tasks.getTaskIndex(index);
    }

    /**
     * Parses the instruction and returns the index to be deleted.
     *
     * @param instruction The instruction to be interpreted.
     * @param tasks The task manager to be used.
     * @return The index of the task to be deleted.
     * @throws InvalidInstructionException If unable to interpret the instruction.
     */
    private static int parseInstruction(String instruction, TaskManager tasks) throws InvalidInstructionException {

        String[] args = instruction.split(" ");
        int index;

        if (args.length < 2) {
            throw new InvalidInstructionException("Oops, the index of the task to be deleted cannot be empty!");
        } else if (args.length > 2) {
            throw new InvalidInstructionException("Oops, there should be only one index of task after 'delete'.");
        }

        try {
            index = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new InvalidInstructionException("Oops, delete operation only accepts integer index!");
        }

        if (!tasks.isValidIndex(index)) {
            throw new InvalidInstructionException("Oops, the task provided doesn't exist!");
        }

        return index;
    }

    /**
     * Performs deletion of the task.
     *
     * @param ui The UI to be used.
     */
    @Override
    public void act(Ui ui) {

        tasks.deleteIndex(toDeleteIndex);

        ui.printMessage("You have successfully deleted:\n"
                + this.toDelete.toString());
    }

    /**
     * Performs the instruction and returns the output message to be printed to the GUI.
     * This method is written for GUI only. If the input is supplied to GUI,the output
     * will not be printed to the output stream.
     *
     * @return The output message to the GUI.
     */
    public String getOutputMessage() {

        tasks.deleteIndex(toDeleteIndex);

        return "You have successfully deleted:\n"
                + this.toDelete.toString();
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
