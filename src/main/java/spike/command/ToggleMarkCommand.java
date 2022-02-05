package spike.command;

import spike.task.Task;
import spike.task.TaskList;

/**
 * Changes completion status of a task.
 */
public class ToggleMarkCommand extends Command {
    public static final String MSG_TASK_DONE = "Great! One more task done:\n";
    public static final String MSG_TASK_UNDONE = "Oops, I've marked this task as not done yet:\n";
    private Task task;
    private int action;

    /**
     * Constructor using action and task.
     *
     * @param action type of action mark/unmark
     * @param task the relevant task
     * @return a command to change completion status
     */
    public ToggleMarkCommand(int action, Task task) {
        this.action = action;
        this.task = task;
    }

    /**
     * Returns the task involved in marking/unmarking.
     *
     * @param tasks current task list
     * @return response message after execution
     */
    @Override
    public String execute(TaskList tasks) {
        if (action == 1) {
            task.markAsDone();
            return MSG_TASK_DONE + task.toString();
        } else {
            task.markAsNotDone();
            return MSG_TASK_UNDONE + task.toString();
        }
    }
}
