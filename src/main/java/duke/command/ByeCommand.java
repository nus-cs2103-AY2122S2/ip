package duke.command;

import duke.util.Constants;
import duke.util.Storage;
import duke.util.TasksList;


public class ByeCommand extends Command {
    @Override
    public String execute(TasksList taskList, Storage storage) {
        return Constants.BYE;
    }
}
