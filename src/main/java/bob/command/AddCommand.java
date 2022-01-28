package bob.command;

import bob.Storage;
import bob.Task.Task;
import bob.TaskList;
import bob.Ui;

/**
 * {@inheritDoc}
 */
public abstract class AddCommand extends Command {
    private String taskName;

    /**
     * Constructor for the AddCommand class.
     * @param taskName the name of the task
     */
    public AddCommand(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Adds and updates the task list and store, with the respective task created by a command.
     * @param task a task the user wants to record
     * @param tasks a List of tasks
     * @param ui a class that deals with interactions with the user
     * @param store represents the file storage of the class
     */
    public void addAndUpdate(Task task, TaskList tasks, Ui ui, Storage store) {
        tasks.addTask(task);
        store.updateStore(tasks);
        ui.newTask(task, tasks);
    }
}
