package ari.command;

import ari.tasks.TaskList;

/**
 * Represents an executable command
 */
public abstract class Command {
    protected TaskList taskList;

    /**
     * Returns message that is going to be displayed when the command is executed
     *
     * @return message that is going to be displayed when the command is executed
     */
    public abstract String execute();

    /**
     * Sets the TaskList for execution of commands
     *
     * @param taskList list of Tasks for execution of commands
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}
