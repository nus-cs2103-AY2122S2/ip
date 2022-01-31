package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Deals with handling command that add tasks.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task the task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task into task list and print out message to inform user.
     *
     * @param tasks List of the tasks.
     * @param ui UI that deals with interactions with the user.
     * @param storage storage handles the saving and writing to file.
     * @return message stating task adeed.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        return ui.showTaskAdded(task, tasks.getTaskList());
    }

    /**
     * Checks if the user is exiting the program.
     *
     * @return false that user not exiting.
     */
    public boolean isExit() {
        return false;
    }
}
