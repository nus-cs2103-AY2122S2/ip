package duke.command;

import duke.TaskList;

public abstract class TaskListCommand extends Command {
    protected TaskList taskList;

    public TaskListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}
