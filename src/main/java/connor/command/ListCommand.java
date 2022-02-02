package connor.command;

import connor.task.TaskList;

/**
 * Represents a List {@code Command}.
 */
public class ListCommand extends Command {
    public ListCommand() {}

    /**
     * Lists the {@code Task}s in the task list.
     */
    @Override
    public String activate() {
        return TaskList.viewTasks();
    }

}
