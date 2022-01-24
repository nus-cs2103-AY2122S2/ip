package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a collection of information that is created after
 * the execution of commands. A <code>CommandFeedback</code> object can store the
 * command type, the task list and the task of the result of a command execution.
 */
public class CommandFeedback {
    public final CommandType cType;
    public final TaskList taskList;
    public final Task task;

    /**
     * Creates an instance of a CommandFeedback object.
     *
     * @param cType the type of command executed.
     */
    public CommandFeedback(CommandType cType) {
        this(cType, null, null);
    }

    /**
     * Creates an instance of a CommandFeedback object.
     *
     * @param cType the type of command executed.
     * @param taskList the affected list of task.
     */
    public CommandFeedback(CommandType cType, TaskList taskList) {
        this(cType, taskList, null);
    }

    /**
     * Creates an instance of a CommandFeedback object.
     *
     * @param cType the type of command executed.
     * @param task the affected task.
     */
    public CommandFeedback(CommandType cType, Task task) {
        this(cType, null, task);
    }

    /**
     * Creates an instance of a CommandFeedback object.
     *
     * @param cType the type of command executed.
     * @param taskList the affected list of task.
     * @param task the affected task.
     */
    public CommandFeedback(CommandType cType, TaskList taskList, Task task) {
        this.cType = cType;
        this.taskList = taskList;
        this.task = task;
    }

}

