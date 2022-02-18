package duke.command;

import duke.ContactList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Mark command. Marks specified task as done.
 */
public class MarkCommand extends Command {

    private int taskNumber;

    /**
     * Construct MarkCommand.
     *
     * @param taskNumber Id of task to delete.
     */
    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Marks task with specified id as done.
     *
     * @param tasks List to add task to.
     * @param ui Interface to display results to.
     * @param storage File storage of tasks.
     * @param contacts List of contacts.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, ContactList contacts) {
        tasks.markTask(taskNumber);
        return ui.showTaskMarked();
    }

}
