package meep.task;


/**
 * Represents the task.
 */
public class Task {
    private final String title;
    private Boolean isDone;


    /**
     * Constructor for class Task.
     *
     * @param title title of task.
     */
    protected Task(String title) {
        this.title = title;
        this.isDone = false;
    }


    /**
     * Constructor for class Task.
     *
     * @param title title of task.
     * @param isDone  status of the task.
     */
    protected Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
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
     * Gets isDone.
     *
     * @return the isDone.
     */
    public Boolean isDone() {
        return isDone;
    }

    /**
     * Mark task isDone.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmark task.
     */
    public void unmarkDone() {
        this.isDone = false;
    }


    /**
     * To String.
     */
    public String toString() {
        String isDone = (this.isDone) ? "X" : " ";
        return "[" + isDone + "] " + getTitle();
    }
}
