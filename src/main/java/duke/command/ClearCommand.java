package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.AlertUi;
import duke.ui.MessageUi;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Represents commands which clears the task list. A ClearCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */
public class ClearCommand implements Command {

    @Override
    public String execute(TaskList tasks, Storage storage, MessageUi ui) throws DukeException {
        try {
            Alert alert = AlertUi.makeConfirmationAlert("Clear task list?",
                    ui.showClearListConfirmationMessage());
            if (alert.showAndWait().get() == ButtonType.OK) {
                tasks.clearTaskList(tasks, storage);
            }
            AlertUi.makeInformationAlert("Task list cleared", ui.showClearListMessage());
            return ui.showClearListMessage();
        } catch (DukeException error) {
            throw new DukeException(error.getMessage());
        }
    }
}
