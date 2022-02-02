package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed prepares the application for getting commands from user.
 */
public class SetupCommand extends Command {
    /**
     * Loads the data stored in disk (if any) into <code>taskList</code>.
     * After that, prompts <code>ui</code> to greet the user.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     * @throws DukeException when an exception is thrown in the process of executing this command.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            taskList.populateWith(storage.loadTasksFromFile());
        } catch (DukeException exception) {
            ui.showErrorMessage(exception.getMessage());
            taskList.clear();
        }

        ui.showMessage("Hello! I am Duke");
        ui.showMessage("How can I help you?");
    }
}
