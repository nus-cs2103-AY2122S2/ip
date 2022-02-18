package duke.command;

import duke.ContactList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Delete command. Deletes a task from the list.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Constructs delete command.
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the task with the specified id.
     *
     * @param tasks List to add task to.
     * @param ui Interface to display results to.
     * @param storage File storage of tasks.
     * @param contacts List of contacts.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, ContactList contacts) {
        tasks.deleteTask(taskNumber);
        return ui.showTaskDeleted();
    }

}
