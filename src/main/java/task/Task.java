package task;

/** Abstract class for categorising different Tasks.
 *  Holds a description and the status of whether its done or not.
 */
public abstract class Task {
    public String description;
    public boolean isDone;

    public Task (String task, boolean isDone) {
        this.description = task;
        this.isDone = isDone;
    }

    /**
     * Returns String that is formatted for a way where its written to the file.
     * @return Formatted String that represents the Task.
     */
    public abstract String fileFormat();
}