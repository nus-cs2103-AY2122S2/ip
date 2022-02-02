package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ToggleCommand extends Command {

    private final String cmd;
    private final String entryNumber;

    /**
     * Instantiates a new Toggle command.
     *
     * @param cmd   to indicate whether to `mark` or `unmark` a `Task`
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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.isEmpty()) {
                ui.printWithDivider("Your list is empty!");
                return;
            }

            int toggleIndex = Parser.parseInt(entryNumber) - 1;
            if (toggleIndex < 0 || toggleIndex >= tasks.size()) {
                ui.showError("Invalid entry number!");
            } else {
                Task toggleTask = tasks.get(toggleIndex);
                toggleTask.setMarked(cmd);
                ui.showToggleTask(toggleTask);
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }
}
