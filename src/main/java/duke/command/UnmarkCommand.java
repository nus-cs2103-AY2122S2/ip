package duke.command;

import duke.ContactList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * UnmarkCommand. Marks a task as not done.
 */
public class UnmarkCommand extends Command {

    private int taskNumber;

    /**
     * Constructs UnmarkTask.
     *
     * @param taskNumber Index of task to unmark.
     */
    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Marks task with specified id as not done.
     *
     * @param tasks List to add task to.
     * @param ui Interface to display results to.
     * @param storage File storage of tasks.
     * @param contacts List of contacts.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, ContactList contacts) {
        tasks.unmarkTask(taskNumber);
        return ui.showTaskUnmarked();
    }

}
