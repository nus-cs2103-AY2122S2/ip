/**
 * Class that encapsulates a Task
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /*
     * Constructor that accepts a String as description
     */
    public Task(String description) {
        this.description = description;
        setDone(false);
    }

    /*
     * Setting for done variable of Task
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }
    public abstract String formatSave();

    /*
     * Customized toString method to display done status of task as well as the task description
     */
    @Override
    public String toString() {
        return "[" +(isDone ? "X" : " ") + "] " + this.description;
    }
}
