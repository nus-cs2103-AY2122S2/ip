package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.showTaskList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
