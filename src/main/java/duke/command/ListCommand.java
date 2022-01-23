package duke.command;

import duke.helpTool.Storage;
import duke.helpTool.TaskList;
import duke.helpTool.Ui;

public class ListCommand extends Command{

    public ListCommand(){

    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
