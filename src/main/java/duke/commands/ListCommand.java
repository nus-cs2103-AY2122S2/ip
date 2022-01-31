package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tl, Storage sge) {
        return tl.getTasksMsg();
    }
}
