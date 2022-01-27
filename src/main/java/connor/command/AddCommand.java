package connor.command;

import connor.task.TaskType;
import connor.task.TaskList;

/**
 * Represents an Add {@code Command}.
 */
public class AddCommand extends Command {
    private TaskType tt;
    private String desc;

    public AddCommand(TaskType tt, String desc) {
        this.tt = tt;
        this.desc = desc;
    }

    /**
     * Adds a {@code Task} to the task list.
     */
    @Override
    public void activate() {
        TaskList.addTask(tt, desc);
    }


}
