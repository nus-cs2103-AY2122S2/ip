package duke.command;

import duke.Storage;
import duke.TasksList;

public class ListCommand extends Command {
    @Override
    public String execute(TasksList taskList, Storage storage) {
        return taskList.list();
    }
}
