package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Represents a command to Add a new Task.
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task t) {
        this.taskToAdd = t;
    }

    /**
     * Executes and adds a Task to TaskManager. Throws a Duke Exception
     * if the task entered is in the wrong format. Returns true if the command executed successfully,
     * false otherwise.
     *
     * @param storage To Storage to save after the command executes.
     * @param ui The Ui to display the output to.
     * @param taskManager The TaskManager that contains the Task object.
     * @return true if command executed successfully, false otherwise.
     * @throws DukeException If format of the task is wrong.
     */
    public String execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        taskManager.addTask(taskToAdd);
        save(storage, ui, taskManager);
        return ui.showAddedTask(taskToAdd, taskManager.size());
    }
}
