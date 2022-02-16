package bro.commands;

import bro.Storage;
import bro.TaskManager;
import bro.Ui;
import bro.exceptions.BroException;
import bro.tasks.Task;

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
     * @throws BroException If format of the task is wrong.
     */
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) {
        assert taskToAdd != null : "No task to add in AddCommand!";
        taskManager.addTask(taskToAdd);
        save(storage, ui, taskManager);
        this.response = ui.showAddedTask(taskToAdd, taskManager.size());
        return true;
    }
}
