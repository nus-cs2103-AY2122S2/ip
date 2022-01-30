package instruction;

import task.Task;
import task.TaskManager;
import ui.Ui;

final class Delete extends Instruction {

    int toDeleteIndex;
    Task toDelete;

    /**
     * Instantiates an instruction "delete", with the given line of instruction.
     *
     * @param instruction The line of instruction for deletion, starting with 'delete'.
     * @param tasks The task manager used.
     * @throws InvalidInstructionException If the given instruction does not contain a valid index.
     */
    Delete(String instruction, TaskManager tasks) throws InvalidInstructionException {
        this(parseInstruction(instruction, tasks), tasks);
    }

    private Delete(int index, TaskManager tasks) {
        super("delete", tasks);
        this.toDeleteIndex = index;
        this.toDelete = tasks.getTaskIndex(index);
    }

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
     * @return The message after deletion.
     */
    @Override
    public void act(Ui ui) {

        tasks.deleteIndex(toDeleteIndex);

        ui.printMessage("You have successfully deleted:\n" +
                this.toDelete.toString());
    }
}
