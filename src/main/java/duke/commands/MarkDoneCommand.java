package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkDoneCommand extends Command{
    private int indexToMark;

    public MarkDoneCommand(int index) {
        this.indexToMark = index;
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
    public String execute(Storage storage, Ui ui, TaskManager taskManager) throws DukeException {
        try {
            if (taskManager.size() == 0) {
                return ui.showMarkEmptyList();
            }

            if (this.indexToMark < 0 || indexToMark >= taskManager.size()) {
                return ui.showMarkOutOfBounds();
            }
            boolean isSuccess = taskManager.markTaskDone(indexToMark);
            if (isSuccess) {
                save(storage, ui, taskManager);
                return ui.showMarked(taskManager.getTask(indexToMark));
            }

            return ui.showMarkNotNeeded(taskManager.getTask(indexToMark));
        } catch (NumberFormatException e) {
            return ui.showInvalidIntegerError();
        }
    }
}
