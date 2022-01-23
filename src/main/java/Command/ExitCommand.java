package Command;

import DukeUtils.Storage;
import DukeUtils.TaskList;
import DukeUtils.Ui;

public class ExitCommand extends Command {
    private static boolean isExit = false;

    public ExitCommand(boolean isExit) {
        ExitCommand.isExit = isExit;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (isExit) {
            ui.exited();
        }
    }

    public static boolean getIsExit() {
        return isExit;
    }
}
