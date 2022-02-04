package spike.command;

import spike.task.Task;
import spike.task.TaskList;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
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
        assert task != null : "Task should not be null";
        tasks.addTask(task);
        return getAddedTaskText(task, tasks);
    }

    /**
     * Returns the message needed for printing when adding task.
     */
    private String getAddedTaskText(Task task, TaskList tasks) {
        String result = "Got it. I've added this task:\n"
                + String.format("    %s\n", task.toString())
                + String.format("Now you have %s task(s) in the list.", tasks.getTasks().size());
        return result;
    }
}

