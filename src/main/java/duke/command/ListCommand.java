package duke.command;

import duke.stack.CallStack;
import duke.task.TaskList;

/**
 * Represents a command that
 * will list all the tasks to the tasklist
 */
public class ListCommand extends Command {
    @Override
    public String executeCommand(TaskList taskList, CallStack callStack) {
        return taskList.getTasks();
    }
}
