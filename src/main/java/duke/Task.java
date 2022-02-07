package duke;
import java.io.Serializable;

public class Task implements Serializable {
    protected String name;
    protected boolean isDone;
    protected final String mark = "[X]";
    protected final String unmarked = "[ ]";

    /**
     * Initializes a new Task class with the name.
     *
     * @param name The name of the Task needed by the user.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public void setMarked() {
        this.isDone = true;
    }

    public void setUnmarked() {
        this.isDone = false;
    }

    /**
     * Checks whether the task has been marked done or not and returns it so.
     *
     * @return The respective string of a marked / un-marked task.
     */
    public String isDone() {
        if (this.isDone) {
            return mark;
        } else {
            return unmarked;
        }
    }

    @Override
    public String toString() {
        return isDone() + " " + getName();
    }
}
