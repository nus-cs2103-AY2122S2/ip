import java.io.IOException;

/**
 * Updates a task.
 */
public class UpdateCommand extends Command {
    /**
     * Completion status of the task.
     */
    protected boolean toComplete;

    /**
     * Index of the task.
     */
    protected int index;

    /**
     * Constructs an update command.
     *
     * @param toComplete completion status of the task.
     * @param index index of the task.
     */
    public UpdateCommand(boolean toComplete, int index) {
        this.toComplete = toComplete;
        this.index = index;
    }

    public String execute() throws InvalidParameterException, IOException {
        if (this.index > super.taskList.getSize()) {
            throw new InvalidParameterException("☹ OOPS!!! The index provided is invalid.");
        }

        if (toComplete) {
            Task task = super.taskList.completeTask(index);
            storage.updateTask(task.getId(), true);
            return String.format("Nice! I've marked this task as done:\n%s", task.toString());
        } else {
            Task task = super.taskList.undoTask(index);
            storage.updateTask(task.getId(), false);
            return String.format("OK, I've marked this task as not done yet:\n%s", task.toString());
        }
    }
}
