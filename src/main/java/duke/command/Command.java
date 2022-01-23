package duke.command;

import duke.task.Task;
import duke.functionality.TaskList;

public abstract class Command{
    protected Task task;
    protected Integer index;
    protected String word;

    public Command(Task t, Integer number, String word) {
        this.task = t;
        this.index = number;
        this.word = word;
    }

    public abstract void execute(TaskList tasks);

    public abstract boolean isExit();
}
