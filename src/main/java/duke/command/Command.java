package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command to be executed in Duke.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList the task list to execute this command on.
     * @param ui the text UI of Duke.
     * @param storage the storage of Duke.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Checks whether the Duke application should exit after this command.
     *
     * @return true iff this Command is a ByeCommand instance.
     */
    public abstract boolean isExit();

    /**
     * Saves the contents of the task list to the file referenced in the storage object.
     *
     * @param taskList the task list to save
     * @param ui the text UI of Duke.
     * @param storage the storage object that references the file to save the data to.
     * @return an empty String if save is successful and an error message otherwise.
     */
    protected String saveData(TaskList taskList, Ui ui, Storage storage) {
        try {
            storage.saveData(taskList);
        } catch (duke.exception.DukeException e) {
            return ui.showSavingError(e.getMessage());
        }
        return "";
    }
}
