package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showTaskList(tasks);
    }
}
