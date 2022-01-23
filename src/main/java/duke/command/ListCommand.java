package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
