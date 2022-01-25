package kidsnd274.duke.command;

import kidsnd274.duke.TaskList;
import kidsnd274.duke.taskobjects.Task;

public abstract class TaskListCommand extends Command {
    protected TaskList taskList;

    public TaskListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}
