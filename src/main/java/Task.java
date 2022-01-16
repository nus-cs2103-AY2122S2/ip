/**
 * Class that encapsulates a Task
 */
public class Task {
    private final String description;
    private boolean isDone;

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

    /*
     * Customized toString method to display done status of task as well as the task description
     */
    @Override
    public String toString() {
        return "[" +(isDone ? "X" : " ") + "] " + this.description;
    }
}
