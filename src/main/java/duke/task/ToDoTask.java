package duke.task;

/**
 * A basic task to track its completion status.
 */
public class ToDoTask extends Task{
    public ToDoTask(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public ToDoTask(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String toFileString() {
        return String.format("%s,%s,%s,", "T", this.isDone, this.taskName);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone?"X":" ", this.taskName);
    }
}
