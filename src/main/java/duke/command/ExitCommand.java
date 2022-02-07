package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.AlertUi;
import duke.ui.MessageUi;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Represents commands which tells the main programme to exit. An ExitCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */
public class ExitCommand implements Command {

    /**
     * Output an exit message to the user.
     *
     * @param tasks   Task object.
     * @param storage Storage object.
     * @param ui      Ui object.
     */

    @Override
    public String execute(TaskList tasks, Storage storage, MessageUi ui) {
        Alert alert = AlertUi.makeConfirmationAlert("Confirm exit", "Do you want to exit Ekud?" );
        if (alert.showAndWait().get() == ButtonType.OK) {
            AlertUi.makeInformationAlert("Farewell!", ui.showExitMessage());
            System.exit(0);
        }
        return ui.showExitMessage();
    }
}
