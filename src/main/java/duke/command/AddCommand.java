package duke.command;

import duke.task.Task;
import duke.util.Save;
import duke.util.TaskList;

/**
 * This AddCommand class will add a task when executed.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand which adds the provided task.
     *
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes command by adding task into TaskList.
     *
     * @param tasks TaskList of tasks.
     * @param save  Saved history.
     */
    @Override
    public String execute(TaskList tasks, Save save) {
        tasks.add(task);
        String response = "Got it. I've added this task:\n"
                + task.track() + task.getStatus() + " " + task.toString()
                + "\nNow you have " + (tasks.getCount()) + " tasks in the list.";
        return response;
    }
}
