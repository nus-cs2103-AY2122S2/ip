package connor.command;

import connor.task.TaskList;

/**
 * Represents a Clear {@code Command}.
 */
public class ClearCommand extends Command {
    public ClearCommand() {}

    /**
     * Clears all {@code Task}s in the task list.
     */
    @Override
    public void activate() {
        TaskList.clearTasks();
    }
}
