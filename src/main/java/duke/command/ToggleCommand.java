package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ToggleCommand extends Command {

    protected String cmd;
    protected String index;

    public ToggleCommand(String cmd, String index) {
        this.cmd = cmd;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (tasks.isEmpty()) {
                ui.printWithDivider("Your list is empty!");
                return;
            }

            int toggleIndex = Parser.parseInt(index) - 1;
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

    @Override
    public boolean isExit() {
        return false;
    }
}
