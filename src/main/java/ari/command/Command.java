package ari.command;

import ari.tasks.TaskList;

public abstract class Command {
    protected TaskList taskList;

    public abstract String execute();

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}
