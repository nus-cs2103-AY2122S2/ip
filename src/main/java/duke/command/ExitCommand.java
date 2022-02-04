package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    @Override public boolean isExit() {
        return true;
    }
}
