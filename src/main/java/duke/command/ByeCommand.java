package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed prepares the application for exiting.
 */
public class ByeCommand extends Command {
    /**
     * Prompts <code>ui</code> to show goodbye message to user.
     * After that, gets the formatted task list from <code>taskList</code>
     * and directs <code>storage</code> to store it into disk.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     * @throws DukeException when an exception is thrown in the process of executing this command.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        ui.showMessage("Bye. Hope to see you again soon!");
        storage.saveToFile(taskList.formatAsFileData());
    }

    /**
     * Checks if this command is a goodbye command.
     *
     * @return true.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
