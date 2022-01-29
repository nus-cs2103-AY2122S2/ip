/**
 * Represents the 'add' instruction to Duke.
 */
final class Add extends Instruction{

    private Task task;

    /**
     * Constructor for an add instruction.
     *
     * @param task The task to be added.
     * @param tasks The task manager to be used.
     */
    protected Add(Task task, TaskManager tasks) throws IllegalArgumentException {

        super("add", tasks);
        this.task = task;
    }

    /**
     * Adds the task into the task manager, and return the message when the action is done.
     *
     * @param ui The UI to be used.
     */
    @Override
    protected void act(Ui ui) {
        ui.printMessage(tasks.addTask(this.task));
    }
}
