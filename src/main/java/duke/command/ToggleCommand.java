package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * An instance of ToggleCommand.
 */
public class ToggleCommand extends Command {

    private final String cmd;
    private final String entryNumber;

    /**
     * Instantiates a new Toggle command.
     *
     * @param cmd         to indicate whether to `mark` or `unmark` a `Task`
     * @param entryNumber the entry number of the `Task` to `mark` or `unmark`
     */
    public ToggleCommand(String cmd, String entryNumber) {
        this.cmd = cmd;
        this.entryNumber = entryNumber;
    }

    /**
     * Sets the status the `Task` according to the {@link #cmd}.
     *
     * @param tasks   the tasks in `TaskList`
     * @param ui      the UI that the user interacts with
     * @param storage the storage that is used to read/write to the local file
     * @return the associated message to show that the TaskList is empty, or if the command has been marked/unmarked.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.isEmpty()) {
                return "Your list is empty!";
            }

            int toggleIndex = Parser.parseInt(entryNumber) - 1;
            if (toggleIndex < 0 || toggleIndex >= tasks.size()) {
                return ui.showError("Invalid entry number!");
            } else {
                Task toggleTask = tasks.get(toggleIndex);
                toggleTask.setMarked(cmd);
                return ui.showToggleTask(toggleTask);
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
