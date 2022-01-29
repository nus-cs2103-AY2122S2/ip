final class Delete extends Instruction {

    int toDeleteIndex;
    Task toDelete;

    /**
     * Instantiates an instruction "delete", with the given line of instruction.
     *
     * @param instruction The line of instruction for deletion, starting with 'delete'.
     * @param tasks The task manager used.
     * @throws IllegalArgumentException If the given instruction does not contain a valid index.
     */
    Delete(String instruction, TaskManager tasks) throws IllegalArgumentException {
        this(parseInstruction(instruction, tasks), tasks);
    }

    private Delete(int index, TaskManager tasks) {
        super("delete", tasks);
        this.toDeleteIndex = index;
        this.toDelete = tasks.getTaskIndex(index);
    }

    private static int parseInstruction(String instruction, TaskManager tasks) throws IllegalArgumentException {

        String[] args = instruction.split(" ");
        int index;

        if (args.length < 2) {
            throw new IllegalArgumentException("Oops, the index of the task to be deleted cannot be empty!");
        } else if (args.length > 2) {
            throw new IllegalArgumentException("Oops, there should be only one index of task after 'delete'.");
        }

        try {
            index = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Oops, delete operation only accepts integer index!");
        }

        if (!tasks.isValidIndex(index)) {
            throw new IllegalArgumentException("Oops, the task provided doesn't exist!");
        }

        return index;
    }

    /**
     * Performs deletion of the task.
     *
     * @return The message after deletion.
     */
    @Override
    protected String act() {

        tasks.deleteIndex(toDeleteIndex);

        return "You have successfully deleted:\n" + this.toDelete.toString();
    }
}
