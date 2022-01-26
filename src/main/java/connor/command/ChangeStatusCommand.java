package connor.command;

import connor.task.TaskStatus;
import connor.task.TaskList;

/**
 * Represents a Change Status {@code Command}.
 */
public class ChangeStatusCommand extends Command {
    TaskStatus ts;
    int index;

    public ChangeStatusCommand(TaskStatus ts, int index) {
        this.ts = ts;
        this.index = index;
    }

    /**
     * Changes the status of the {@code Task} in the task list with the given index.
     */
    @Override
    public void activate() {
        TaskList.markStatus(ts, index);
    }
}
