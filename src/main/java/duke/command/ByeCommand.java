package duke.command;

import duke.util.Storage;
import duke.util.TasksList;
import duke.util.Constants;

public class ByeCommand extends Command {
    @Override
    public String execute(TasksList taskList, Storage storage) {
        return Constants.BYE;
    }
}
