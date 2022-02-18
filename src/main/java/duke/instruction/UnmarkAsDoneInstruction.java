package duke.instruction;

import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents the instruction "unmark".
 */
final class UnmarkAsDoneInstruction extends Instruction {

    private static final String HELP_MESSAGE = "unmark: mark a task with given index as not done.\n"
            + "usage: unmark <task_id>";
    private final Task toUnmark;

    /**
     * Constructor 1. Initializes the instruction using an index of task.
     *
     * @param index The index of the task.
     * @param tasks The task manager to be used.
     */
    private UnmarkAsDoneInstruction(int index, TaskManager tasks) {
        super("unmark", tasks);
        this.toUnmark = tasks.getTaskIndex(index);
    }

    /**
     * Constructor 2. Takes in the whole instruction line and initializes the
     * instruction.UnmarkAsDoneInstruction instruction.
     *
     * @param instruction The line of instruction. It has to be guaranteed that the first word is 'unmark'.
     * @param tasks The task manager to be used.
     * @throws InvalidInstructionException If the instruction cannot be interpreted.
     */
    protected UnmarkAsDoneInstruction(String instruction, TaskManager tasks) throws InvalidInstructionException {

        this(UnmarkAsDoneInstruction.parseInstruction(instruction, tasks), tasks);
    }

    /**
     * Takes in a line of instruction (starting with a word that invokes this class), then parses it and returns the
     * index of task to be marked.
     *
     * @param instruction The line of instruction.
     * @return The index of the object to be marked.
     * @throws InvalidInstructionException If (i) the instruction has no valid integer to be parsed;
     * or (ii) the index is out of range.
     */
    private static int parseInstruction(String instruction, TaskManager tasks) throws InvalidInstructionException {

        String[] args = instruction.split(" ", 2);
        int index;

        if (args.length < 2) {
            throw new InvalidInstructionException("Oops, the index of the task to be unmarked cannot be empty!");
        } else if (args.length > 2) {
            throw new InvalidInstructionException("Oops, there should be only one index of task after 'unmark'.");
        }

        try {
            index = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new InvalidInstructionException("Oops, unmark operation only accepts integer index!");
        }

        if (!tasks.isValidIndex(index)) {
            throw new InvalidInstructionException("Oops, the task provided doesn't exist!");
        }

        return index;
    }

    /**
     * Performs the action of marking a task as not done, and prints the message to the UI.
     *
     * @param ui The UI to be used by this instruction.
     */
    @Override
    public void act(Ui ui) {
        TaskManager.markAsNotDone(this.toUnmark);
        ui.printMessage("I've marked this task as not done yet:\n" + this.toUnmark);
    }

    /**
     * Performs the instruction and returns the output message to be printed to the GUI.
     * This method is written for GUI only. If the input is supplied to GUI,the output
     * will not be printed to the output stream.
     *
     * @return The output message to the GUI.
     */
    public String getOutputMessage() {

        TaskManager.markAsNotDone(this.toUnmark);
        return "I've marked this task as not done yet:\n" + this.toUnmark;
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
