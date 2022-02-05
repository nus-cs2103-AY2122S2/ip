package duke.commands;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskManager;
import duke.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkDoneCommand extends MarkCommand {
    public MarkDoneCommand(String userInput) {
        super(userInput);
    }

    /**
     * Executes and marks the Task as not done. If an invalid number is provided,
     * throws a Duke Exception containing the error message. Returns true
     * if executed successfully, false otherwise.
     *
     * @param storage To Storage to save after the command executes.
     * @param ui The Ui to display the output to.
     * @param taskManager The TaskManager that contains the Task object.
     * @return true if command executed successfully, false otherwise.
     * @throws DukeException If index entered is not a number.
     */
    @Override
    public String execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        try {
            if (taskManager.size() == 0) {
                return ui.showMarkEmptyList();
            }

            int index = Integer.parseInt(userInput.replaceFirst("mark", "").strip()) - 1;

            if (index < 0 || index >= taskManager.size()) {
                return ui.showMarkOutOfBounds();
            }
            boolean isSuccess = taskManager.markTaskDone(index);
            if (isSuccess) {
                save(storage,ui,taskManager);
                return ui.showMarked(taskManager.getTask(index));
            }

            return ui.showMarkNotNeeded(taskManager.getTask(index));
        } catch (NumberFormatException e) {
            return "Invalid number entered!";
        }
    }
}
