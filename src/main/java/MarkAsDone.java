/**
 * Represents the instruction "mark as done".
 */
final class MarkAsDone extends Instruction {

    private Task toMark;

    /**
     * Constructor 1. Initializes the instruction using an index of task.
     *
     * @param index The index of the task.
     * @param tasks The task manager to be used.
     */
    private MarkAsDone(int index, TaskManager tasks) {

        super("mark", tasks);
        this.toMark = tasks.getTaskIndex(index);
    }

    /**
     * Constructor 2. Takes in the whole instruction line and initializes the MarkAsDone instruction.
     *
     * @param instruction The line of instruction. It has to be guaranteed that the first word is 'mark'.
     * @param tasks The task manager to be used.
     * @throws IllegalArgumentException
     */
    protected MarkAsDone(String instruction, TaskManager tasks) throws IllegalArgumentException {

        this(MarkAsDone.parseInstruction(instruction, tasks), tasks);
    }

    /**
     * Takes in a line of instruction (starting with a word that invokes this class), then parses it and returns the
     * index of task to be marked.
     *
     * @param instruction The line of instruction.
     * @param tasks The task manager used by this instruction.
     * @return The index of the object to be marked.
     * @throws IllegalArgumentException If (i) the instruction has no valid integer to be parsed; or (ii) the index is
     * out of range.
     */
    private static int parseInstruction(String instruction, TaskManager tasks) throws IllegalArgumentException {

        String[] args = instruction.split(" ", 2);
        int index;

        if (args.length < 2) {
            throw new IllegalArgumentException("Oops, the index of the task to be marked cannot be empty!");
        } else if (args.length > 2) {
            throw new IllegalArgumentException("Oops, there should be only one index of task after 'mark'.");
        }

        try {
            index = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Oops, mark operation only accepts integer index!");
        }

        if (!tasks.isValidIndex(index)) {
            throw new IllegalArgumentException("Oops, the task provided doesn't exist!");
        }

        return index;
    }

    @Override
    protected String act() {
        TaskManager.markAsDone(this.toMark);
        return "Nice! I've marked this task as done:\n     " + this.toMark.toString();
    }
}
