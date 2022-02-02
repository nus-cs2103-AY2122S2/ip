package duke.command;

import duke.TaskList;
import duke.taskobjects.Task;

public abstract class AddCommand extends TaskListCommand {
    Task taskObj;

    public AddCommand(TaskList taskList, Task taskObj) {
        super(taskList);
        this.taskObj = taskObj;
    }

}
