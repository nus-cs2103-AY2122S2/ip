package kidsnd274.duke.command;

import kidsnd274.duke.TaskList;
import kidsnd274.duke.taskobjects.Task;

public abstract class AddCommand extends TaskListCommand {
    Task taskObj;

    public AddCommand(TaskList taskList, Task taskObj) {
        super(taskList);
        this.taskObj = taskObj;
    }

}
