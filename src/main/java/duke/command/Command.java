package duke.command;

import duke.exception.DukeException;
import duke.stack.CallStack;
import duke.task.TaskList;

/** Represents Command abstraction */
public abstract class Command {
    /**
     * Executes the a particular task
     * by directly modifies the tasklist
     *
     * @param taskList reference to the tasklist in main program
     * @param callStack callstack to be called for undo operations
     * @return String representation of the task executed
     * @throws DukeException Exceptions if task if index > number of task
     */
    public abstract String executeCommand(TaskList taskList, CallStack callStack) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
