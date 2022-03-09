package duke.command;
import duke.TasksList;
import duke.Storage;

public class ListCommand extends Command {
    @Override
    public String execute(TasksList taskList, Storage storage) {
        return taskList.list();
    }
}
