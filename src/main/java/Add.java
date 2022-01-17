/**
 * Represents the 'add' instruction to Duke.
 */
final class Add extends Instruction{

    private Task task;

    /**
     * Constructor for an add instruction.
     *
     * @param task The task to be added.
     */
    protected Add(Task task) throws IllegalArgumentException {

        super.setDescription("add");
        this.task = task;
    }

    /**
     * Adds the task into the task manager, and return the message when the action is done.
     *
     * @return The message.
     */
    @Override
    protected String act() {
        return TaskManager.addTask(this.task);
    }
}
