package connor.command;

import connor.task.TaskList;
import connor.task.TaskType;

/**
 * Represents an Add {@code Command}.
 */
public class AddCommand extends Command {
    private TaskType tt;
    private String desc;

    /**
     * Constructor for {@code AddCommand} class.
     *
     * @param tt Type of Task.
     * @param desc Description of the task.
     */
    public AddCommand(TaskType tt, String desc) {
        this.tt = tt;
        this.desc = desc;
    }

    /**
     * Adds a {@code Task} to the task list.
     */
    @Override
    public String activate() {
        return TaskList.addTask(tt, desc);
    }


}
