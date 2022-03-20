package duke;
/**
 * Represents the tasks added to the task list
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public String tag;

    public static void main(String[] args) {
    }

    /**
     * Creates a new task with the given description
     * Default value of the isDone is false.
     * @param description describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = null;
    }

    public Task(String description, String tag) {
        this.description = description;
        this.isDone = false;
        this.tag = tag;
    }

    /**
     * To retrieve the status of the task
     * @return status of the task, where X represents done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Changes the value of isDone to be true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes the value of isDone to be false.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * To display the task to the user and data log.
     * @return prints the task in the specified format
     */
    public String toString() {
        if (tag != null){
            return String.format("[%s] %s (%s)", getStatusIcon(), description, tag);
        } else {
            return String.format("[%s] %s", getStatusIcon(), description);
        }

    }
}