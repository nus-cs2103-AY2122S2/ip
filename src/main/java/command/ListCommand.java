package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.outputMessage("Here are the tasks in your list:\n" + taskList);
    }
}
