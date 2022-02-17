package connor.command;

import connor.task.TaskList;
import connor.task.TaskStatus;

/**
 * Represents a Change Status {@code Command}.
 */
public class ChangeStatusCommand extends Command {
    private TaskStatus ts;
    private int index;

    /**
     * Constructor for the ChangeStatusCommand class.
     *
     * @param ts Status of the task to be changed to.
     * @param index Index of the task to be changed.
     */
    public ChangeStatusCommand(TaskStatus ts, int index) {
        this.ts = ts;
        this.index = index;
    }

    /**
     * Changes the status of the {@code Task} in the task list with the given index.
     */
    @Override
    public String activate() {
        return TaskList.markStatus(ts, index);
    }
}
