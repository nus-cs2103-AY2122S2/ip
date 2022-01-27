package dazz.command;

import dazz.Storage;
import dazz.TaskList;
import dazz.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }

}
