package spike.command;

import spike.task.Task;
import spike.task.TaskList;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    public static final String MSG_ADDED_TASK = "Got it. I've added this task:\n";
    public static final String MSG_AFTER_ADDED_TASK = "Now you have %s task(s) in the list.";
    private Task task;

    /**
     * Constructor using a Task object.
     *
     * @param task the task to be added
     * @return a command ready to be executed to add task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     *
     * @param tasks current task list
     * @return execution result string
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.addTask(task);
        return getAddedTaskText(task, tasks);
    }

    /**
     * Returns the message needed for printing when adding task.
     */
    private String getAddedTaskText(Task task, TaskList tasks) {
        String result = MSG_ADDED_TASK
                + String.format("    %s\n", task.toString())
                + String.format(MSG_AFTER_ADDED_TASK, tasks.getTasks().size());
        return result;
    }
}

