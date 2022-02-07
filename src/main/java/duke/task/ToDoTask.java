package duke.task;

/**
 * A basic task to track its completion status.
 */
public class ToDoTask extends Task {

    /**
     * Constructor for TodoTask.
     * @param taskName The name of the Task to be added.
     * @param isDone If the Task that is being added is marked.
     */
    public ToDoTask(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    @Override
    public String toFileString() {
        return String.format("%s,%s,%s,", "T", this.isDone ? "T" : "F", this.taskName);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.taskName);
    }
}
