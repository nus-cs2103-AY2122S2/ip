package task;

/** Abstract class for categorising different Tasks.
 *  Holds a description and the status of whether its done or not.
 */
public abstract class Task {
    public String description;
    public boolean done;

    public Task (String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    /**
     * Returns String that is formatted for a way where its written to the file.
     * @return Formatted String that represents the Task.
     */
    public abstract String fileFormat();
}