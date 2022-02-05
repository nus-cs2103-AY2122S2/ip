package duke.command;

import duke.task.Task;
import duke.util.Save;
import duke.util.TaskList;

/**
 * This DeleteCommand class will delete a task provided with a 0-based index when executed.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Constructor for DeleteCommand which provides an index to delete.
     *
     * @param taskNum 0-based index.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes command by deleting the given indexed task from TaskList.
     *
     * @param tasks TaskList of tasks.
     * @param save  Saved history.
     */
    @Override
    public String execute(TaskList tasks, Save save) {
        String response = "";
        try {
            Task deleteTask = tasks.getTask(this.taskNum);
            tasks.delete(this.taskNum);
            response = "Noted. I've removed this task:\n" + deleteTask.track() + deleteTask.getStatus() + " "
                    + deleteTask + "\nNow you have " + tasks.getCount() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            response = "☹ Woof Woof!!! This task cannot be found!!!";
        }
        return response;
    }
}
