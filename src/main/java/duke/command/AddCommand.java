package duke.command;

import duke.ContactList;
import duke.DukeException;
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
     * @param contacts List of contacts.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, ContactList contacts) {
        try {
            tasks.addTask(task);
            storage.save(tasks);
            return ui.showTaskAdded(task);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

}
