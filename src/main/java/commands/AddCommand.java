package commands;

import task.ListTask;
import task.Task;


/**
 * Adds a task to task list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_EVENT = "event";
    public static final String MESSAGE_ADD = "Got it. I've added this task:\n";

    private final Task task;

    /**
     * Constructor for class AddCommand.
     *
     * @param task task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns successfully added message.
     *
     * @param tasks task list.
     * @return successfully added message.
     */
    @Override
    public String execute(ListTask tasks) {
        tasks.addTask(task);

        return MESSAGE_ADD + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";
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
