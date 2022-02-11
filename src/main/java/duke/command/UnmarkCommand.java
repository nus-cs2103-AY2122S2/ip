package duke.command;

import duke.stack.CallStack;
import duke.task.TaskList;
import duke.exception.InvalidTaskNumberException;

public class UnmarkCommand extends Command {
    private final int id;

    public UnmarkCommand(int id) {
        this.id = id;
    }

    @Override
    public String executeCommand(TaskList taskList, CallStack callStack) throws InvalidTaskNumberException {
        callStack.pushState(taskList);
        return taskList.mark(id, "unmark");
    }
}
