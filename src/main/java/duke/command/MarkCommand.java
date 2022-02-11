package duke.command;

import duke.stack.CallStack;
import duke.task.TaskList;
import duke.exception.InvalidTaskNumberException;

public class MarkCommand extends Command {
    private final int id;

    public MarkCommand(int id) {
        this.id = id;
    }

    @Override
    public String executeCommand(TaskList taskList, CallStack callStack) throws InvalidTaskNumberException {
        callStack.pushState(taskList);
        return taskList.mark(id, "mark");
    }
}
