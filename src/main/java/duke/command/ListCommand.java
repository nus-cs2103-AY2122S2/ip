package duke.command;

import duke.util.Storage;
import duke.util.TasksList;

public class ListCommand extends Command {
    @Override
    public String execute(TasksList taskList, Storage storage) {
        return taskList.list();
    }
}
