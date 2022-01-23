package duke.command;

import duke.task.Task;
import duke.operations.TaskList;

public abstract class Command {
    protected Task task;
    protected Integer num;
    protected String keyword;

    public Command(Task task, Integer num, String keyword) {
        this.task = task;
        this.num = num;
        this.keyword = keyword;
    }

    public abstract void execute(TaskList tasks);
    public abstract boolean isExit();
}
