package duke.task;

import duke.util.Loading;
import duke.util.Saving;

public abstract class Task implements Saving, Loading {
    protected boolean isDone;
    protected String taskDescription;

    public Task() {
        this.isDone = false;
        this.taskDescription = "";
    }

    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X] " : " ] ") + this.taskDescription;
    }
}
