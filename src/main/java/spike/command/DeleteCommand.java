package spike.command;

import spike.task.Task;
import spike.task.TaskList;

/**
 * Deletes a task from the list.
 */
public class DeleteCommand extends Command {
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
     * Removes and printed the deleted task.
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
     */
    private String getDeletedTaskText(Task task, TaskList tasks) {
        String result = " Noted. I've removed this task: \n"
                + String.format("    %s\n", task)
                + String.format("Now you have %s task(s) in the list.", tasks.getTasks().size());
        return result;
    }
}

