import java.time.LocalDate;

/**
 * The Task class handles the interaction and form of tasks.
 *
 * @author Rdac0
 */
public class Task {
    private String name;
    private boolean done;

    /**
     * Creates a Task object.
     *
     * @param text The name of the Task.
     */
    public Task(String text) {
        this.name = text;
        this.done = false;
    }

    public LocalDate getTime() {
        return null;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return done;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.done = true;
    }

    /**
     * Sets the task as undone.
     */
    public void setUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String mark;
        if (this.done) {
            mark = "[X] ";
        } else {
            mark = "[ ] ";
        }
        return "[T]" + mark + this.name;
    }
}
