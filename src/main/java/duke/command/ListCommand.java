package duke.command;

import duke.ContactList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * List command. Shows task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs delete command.
     */
    public ListCommand() {
        super();
    }

    /**
     * Shows list of all tasks.
     *
     * @param tasks List to add task to.
     * @param ui Interface to display results to.
     * @param storage File storage of tasks.
     * @param contacts List of contacts.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage, ContactList contacts) {
        return ui.showTasks(tasks);
    }

}
