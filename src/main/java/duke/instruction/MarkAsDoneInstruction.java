package duke.instruction;

import duke.task.Task;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents the instruction "mark as done".
 */
final class MarkAsDoneInstruction extends Instruction {

    private static final String HELP_MESSAGE = "mark: mark a task with given index as done.\n"
            + "usage: mark <task_id>";

    private final Task toMark;

    /**
     * Constructor 1. Initializes the instruction using an index of task.
     *
     * @param index The index of the task.
     * @param tasks The task manager to be used.
     */
    private MarkAsDoneInstruction(int index, TaskManager tasks) {

        super("mark", tasks);
        this.toMark = tasks.getTaskIndex(index);
    }

    /**
     * Constructor 2. Takes in the whole instruction line and initializes the
     * instruction.MarkAsDoneInstruction instruction.
     *
     * @param instruction The line of instruction. It has to be guaranteed that the first word is 'mark'.
     * @param tasks The task manager to be used.
     * @throws InvalidInstructionException If it cannot find the task to be deleted.
     */
    protected MarkAsDoneInstruction(String instruction, TaskManager tasks) throws InvalidInstructionException {

        this(MarkAsDoneInstruction.parseInstruction(instruction, tasks), tasks);
    }

    /**
     * Takes in a line of instruction (starting with a word that invokes this class), then parses it and returns the
     * index of task to be marked.
     *
     * @param instruction The line of instruction.
     * @param tasks The task manager used by this instruction.
     * @return The index of the object to be marked.
     * @throws InvalidInstructionException If (i) the instruction has no valid integer to be parsed;
     * or (ii) the index is out of range.
     */
    private static int parseInstruction(String instruction, TaskManager tasks) throws InvalidInstructionException {

        String[] args = instruction.split(" ", 2);
        int index;

        if (args.length < 2) {
            throw new InvalidInstructionException("Oops, the index of the task to be marked cannot be empty!");
        } else if (args.length > 2) {
            throw new InvalidInstructionException("Oops, there should be only one index of task after 'mark'.");
        }

        try {
            index = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new InvalidInstructionException("Oops, mark operation only accepts integer index!");
        }

        if (!tasks.isValidIndex(index)) {
            throw new InvalidInstructionException("Oops, the task provided doesn't exist!");
        }

        return index;
    }

    /**
     * Performs the action to mark the encapsulated task as done.
     *
     * @param ui The UI to be used by this instruction.
     */
    @Override
    public void act(Ui ui) {
        TaskManager.markAsDone(this.toMark);
        ui.printMessage("Nice! I've marked this task as done:\n     " + this.toMark);
    }

    /**
     * Performs the instruction and returns the output message to be printed to the GUI.
     * This method is written for GUI only. If the input is supplied to GUI,the output
     * will not be printed to the output stream.
     *
     * @return The output message to the GUI.
     */
    public String getOutputMessage() {

        TaskManager.markAsDone(this.toMark);
        return "Nice! I've marked this task as done:\n     " + this.toMark;
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
