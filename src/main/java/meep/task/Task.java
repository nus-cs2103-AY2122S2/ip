package meep.task;


/**
 * Represents the task.
 */
public class Task {
    private final String title;
    private Boolean done;


    /**
     * Constructor for class Task.
     *
     * @param title title of task.
     */
    protected Task(String title) {
        this.title = title;
        this.done = false;
    }


    /**
     * Constructor for class Task.
     *
     * @param title title of task.
     * @param done  status of the task.
     */
    protected Task(String title, boolean done) {
        this.title = title;
        this.done = done;
    }

    /**
     * Gets title.
     *
     * @return the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets done.
     *
     * @return the done.
     */
    public Boolean getDone() {
        return done;
    }

    /**
     * Mark task done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Unmark task.
     */
    public void unmarkDone() {
        this.done = false;
    }


    /**
     * To String.
     */
    public String toString() {
        String isDone = (done == true) ? "X" : " ";
        return "[" + isDone + "] " + getTitle();
    }
}
