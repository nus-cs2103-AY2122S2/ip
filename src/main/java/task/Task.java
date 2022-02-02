package task;

/**
 * a task which is inherited by ToDo, Deadline or Event
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     *  Create a new Task class
     *
     * @param description string of the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * determine if the task is done
     *
     * @return X if task is done, else " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark the task is done by setting isDone to true
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * mark the task is not done by setting isDone to false
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * string format for the save file
     *
     * @return string format for the save file
     */
    public String saveString() {
        return "X" + "|" + (this.isDone ? "1" : "0") + "|" + this.description;
    }

    /**
     * string format for printing on the UI
     *
     * @return string format for printing on the UI
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
