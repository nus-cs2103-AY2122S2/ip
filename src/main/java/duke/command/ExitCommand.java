package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
