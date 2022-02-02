package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Represents a command to mark a task as not done.
 */
public class MarkUndoneCommand extends MarkCommand {
    public MarkUndoneCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes and marks the Task as done. If an invalid number is provided,
     * throws a Duke Exception containing the error message. Returns true
     * if executed successfully, false otherwise.
     *
     * @param storage To Storage to save after the command executes.
     * @param ui The Ui to display the output to.
     * @param taskManager The TaskManager that contains the Task object.
     * @return true if command executed successfully, false otherwise.
     * @throws DukeException If index entered is not a number.
     */
    public boolean execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        try {
            if (taskManager.size() == 0) {
                ui.showUnmarkEmptyList();
                return false;
            } else {
                int index = Integer.parseInt(userInput.replaceFirst("unmark", "").strip()) - 1;

                if (index < 0 || index >= taskManager.size()) {
                    ui.showUnmarkOutOfBounds();
                    return false;
                } else {
                    boolean isSuccess = taskManager.markTaskUndone(index);
                    if (isSuccess) {
                        ui.showUnmarked(taskManager.getTask(index));
                        save(storage, ui, taskManager);
                    } else {
                        ui.showUnmarkNotNeeded(taskManager.getTask(index));
                    }
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number entered!");
        }
    }
}
