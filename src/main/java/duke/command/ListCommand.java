package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    public ListCommand() {}

    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listTasks();
        return false;
    }
}
