package duke.command;

import duke.stack.CallStack;
import duke.task.TaskList;

/**
 * Represents undo command that
 * will undo program execution
 */
public class UndoCommand extends Command {
    @Override
    public String executeCommand(TaskList taskList, CallStack callStack) {
        TaskList lastState = callStack.popState();
        return taskList.copyStateFrom(lastState);
    }
}
