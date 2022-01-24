package duke.command;

import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;

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
