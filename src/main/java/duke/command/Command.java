package duke.command;

import duke.util.TaskList;

public abstract class Command {
    protected TaskList tasks;

    public abstract String execute();

    public void setData(TaskList taskList) {
        this.tasks = taskList;
    }
}
