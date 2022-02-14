package duke.command;

import duke.stack.CallStack;
import duke.task.TaskList;
import duke.util.Constants;

/**
 * Represents an exit command that
 * when execute, program will exit
 */
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
