package arthur.task;

/**
 * A class that creates task objects
 */
public class Task {
    private final String description;     // Task.Task description
    private boolean isDone;

    /**
     * Constructor for the Task object
     * @param e String input of the task description
     */
    public Task(String e) {
        this.description = e;
    }

    /**
     * Updates the isDone as true to indicate task marked.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Updates the isDone as false to indicate task unmarked
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] >> " + this.description;
        } else {
            return "[ ] >> " + this.description;
        }
    }
}
