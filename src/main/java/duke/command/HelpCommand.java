package duke.command;

import duke.util.Storage;
import duke.util.TasksList;
import duke.util.Constants;

public class HelpCommand extends Command {
    @Override
    public String execute(TasksList taskList, Storage storage) {
        return Constants.HELP;
    }
}
