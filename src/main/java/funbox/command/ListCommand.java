package funbox.command;

import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printTasks(ui);
    }
}
