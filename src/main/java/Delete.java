final class Delete extends Instruction {

    int toDeleteIndex;
    Task toDelete;

    /**
     * Instantiates an instruction "delete", with the given line of instruction.
     *
     * @param instruction The line of instruction for deletion, starting with 'delete'.
     * @throws IllegalArgumentException If the given instruction does not contain a valid index.
     */
    protected Delete(String instruction) throws IllegalArgumentException {
        this(parseInstruction(instruction));
    }

    private Delete(int index) {
        super.setDescription("delete");
        this.toDeleteIndex = index;
        this.toDelete = TaskManager.getTaskIndex(index);
    }

    private static int parseInstruction(String instruction) throws IllegalArgumentException {

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

        if (!TaskManager.isValidIndex(index)) {
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

        TaskManager.deleteIndex(toDeleteIndex);

        return "You have successfully deleted:\n" + this.toDelete.toString();
    }
}
