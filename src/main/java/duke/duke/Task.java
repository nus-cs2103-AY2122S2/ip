package duke.duke;
import java.io.Serializable;

public class Task implements Serializable {
    protected String name;
    protected boolean done;
    protected final String mark = "[X]";
    protected final String unmarked = "[ ]";

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }

    public void setMarked() {
        this.done = true;
    }

    public void setUnmarked() {
        this.done = false;
    }

    public String isDone() {
        if (this.done) {
            return mark;
        }
        else {
            return unmarked;
        }
    }

    @Override
    public String toString() {
        return isDone() + " " + getName();
    }
}
