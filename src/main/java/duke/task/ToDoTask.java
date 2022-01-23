package duke.task;

/**
 * A basic task to track its completion status
 */
public class ToDoTask extends Task{
    public ToDoTask(String ss) {
        this.taskName = ss;
        this.isDone = false;
    }

    public ToDoTask(String ss, boolean b) {
        this.taskName = ss;
        this.isDone = b;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone?"X":" ", this.taskName);
    }
}
