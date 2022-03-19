package pac.command;

import pac.task.TaskList;
import pac.ui.Ui;
import pac.storage.Storage;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
