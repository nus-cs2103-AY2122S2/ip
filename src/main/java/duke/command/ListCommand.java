package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public ListCommand(String[] commandArray) {
        super(commandArray);
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.showUiForTaskList(taskList);
    }
}
