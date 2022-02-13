package duke.command;

import duke.TaskList;
import duke.taskobjects.Task;

public abstract class AddCommand extends TaskListCommand {
    protected Task taskObj;

    /**
     * Creates an AddCommand object.
     *
     * @param taskList Task list where tasks will be added to.
     * @param taskObj Task that will be added to the list.
     */
    public AddCommand(TaskList taskList, Task taskObj) {
        super(taskList);
        this.taskObj = taskObj;
    }
}
