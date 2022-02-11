package duke.command;

import duke.stack.CallStack;
import duke.task.TaskList;
import duke.exception.DukeException;

public abstract class Command {
    public abstract String executeCommand(TaskList taskList, CallStack callStack) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
