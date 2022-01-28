package meep.commands;


import meep.task.ListTask;
import meep.task.Task;

/**
 * Deletes a task in task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task:\n";

    private final int index;

    /**
     * Constructor for class DeleteCommand.
     *
     * @param index index of task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns successfully deleted message.
     *
     * @param tasks task list.
     * @return successfully deleted message.
     */
    @Override
    public String execute(ListTask tasks) {
        Task task = tasks.get(index);
        tasks.deleteTask(index);
        return MESSAGE_DELETE + task.toString()
                + "\nNow you have " + tasks.size()
                + " tasks in the list.";
    }

    /**
     * Returns command word.
     *
     * @return command word.
     */
    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
