package duke.task;

/**
 * Class that encapsulates a duke.task.Task
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /*
     * Constructor that accepts a String as description
     */
    protected Task(String description) {
        this.description = description;
        setDone(false);
    }

    /*
     * Setting for done variable of duke.task.Task
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    public abstract String formatSave();

    public String getDescription() {
        return this.description;
    }

    /*
     * Customized toString method to display done status of task as well as the task description
     */
    @Override
    public String toString() {
        return "[" +(isDone ? "X" : " ") + "] " + this.description;
    }
}
