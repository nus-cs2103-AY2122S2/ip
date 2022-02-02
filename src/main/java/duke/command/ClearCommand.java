package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.MessageUi;

/**
 * Represents commands which clears the task list. A ClearCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */
public class ClearCommand implements Command {

    @Override
    public String execute(TaskList tasks, Storage storage, MessageUi ui) throws DukeException {
        try {
            ui.showClearListConfirmationMessage();
            String response = "y";
            if (response.equals("y")) {
                tasks.clearTaskList(tasks, storage);
            }
            return ui.showClearListMessage(response);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
