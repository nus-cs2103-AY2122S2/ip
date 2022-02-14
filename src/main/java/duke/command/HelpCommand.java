package duke.command;

import duke.exception.DukeException;
import duke.stack.CallStack;
import duke.task.TaskList;
import duke.util.Constants;

public class HelpCommand extends Command {
    @Override
    public String executeCommand(TaskList taskList, CallStack callStack) throws DukeException {
        return Constants.HELP_MESSAGE;
    }
}
