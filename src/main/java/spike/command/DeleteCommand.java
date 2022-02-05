package spike.command;

import spike.task.Task;
import spike.task.TaskList;

/**
 * Deletes a task from the list.
 */
public class DeleteCommand extends Command {
    public static final String MSG_DELETED_TASK = " Noted. I've removed this task: \n";
    public static final String MSG_AFTER_DELETED_TASK = "Now you have %s task(s) in the list.";
    private Task task;

    /**
     * Constructor using a Task object.
     *
     * @param task the task to be deleted
     * @return a command ready to be executed to delete task
     */
    public DeleteCommand(Task task) {
        this.task = task;
    }

    /**
     * Removes and prints the deleted task.
     *
     * @param tasks current task list
     * @return deletion response string
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.deleteTask(task);
        return getDeletedTaskText(task, tasks);
    }

    /**
     * Returns the message needed for printing when deleting task.
     *
     * @return response after deleting task
     */
    private String getDeletedTaskText(Task task, TaskList tasks) {
        String result = MSG_DELETED_TASK
                + String.format("    %s\n", task)
                + String.format(MSG_AFTER_DELETED_TASK, tasks.getTasks().size());
        return result;
    }
}

