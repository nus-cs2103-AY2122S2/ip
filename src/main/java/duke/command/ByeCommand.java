package duke.command;
import duke.TasksList;
import duke.Storage;
import duke.util.Constants;

public class ByeCommand extends Command {
    @Override
    public String execute(TasksList taskList, Storage storage) {
        return Constants.BYE;
    }
}
