package connor.command;

import connor.task.TaskList;
import connor.task.TaskType;

/**
 * Represents an Add {@code Command}.
 */
public class AddCommand extends Command {
    private TaskType taskType;
    private String desc;

    /**
     * Constructor for {@code AddCommand} class.
     *
     * @param taskType Type of Task.
     * @param desc Description of the task.
     */
    public AddCommand(TaskType taskType, String desc) {
        this.taskType = taskType;
        this.desc = desc;
    }

    /**
     * Adds a {@code Task} to the task list.
     */
    @Override
    public String activate() {
        return TaskList.addTask(taskType, desc);
    }


}
