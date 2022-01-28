package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {
    public ListCommand(){}

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        taskList.list();
        return true;
    }
}
