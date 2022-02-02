package duke.command;

import duke.TaskList;
import duke.taskobjects.Task;

public abstract class AddCommand extends TaskListCommand {
    protected Task taskObj;

    /**
     * Default constructor for AddCommand.
     * @param taskList Provided task list where tasks are added to
     * @param taskObj The task that will be added to the task list
     */
    public AddCommand(TaskList taskList, Task taskObj) {
        super(taskList);
        this.taskObj = taskObj;
    }
}
