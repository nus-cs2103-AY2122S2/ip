package helperClasses.command;

import helperClasses.Storage;
import helperClasses.TaskList;
import helperClasses.Ui;

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
