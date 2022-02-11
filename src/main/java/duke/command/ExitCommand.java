package duke.command;

import duke.stack.CallStack;
import duke.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public String executeCommand(TaskList taskList, CallStack callStack) {
        return "";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
