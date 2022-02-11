package duke.command;

import duke.stack.CallStack;
import duke.task.TaskList;
import duke.util.Constants;

public class ExitCommand extends Command {
    @Override
    public String executeCommand(TaskList taskList, CallStack callStack) {
        return Constants.BYE;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
