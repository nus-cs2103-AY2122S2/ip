package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs AddCommand.
     *
     * @param task Task to add to the list.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Adds the specified task to the list.
     *
     * @param tasks List to add task to.
     * @param ui Interface to display results to.
     * @param storage File storage of tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.showTaskAdded(task);
    }

}
