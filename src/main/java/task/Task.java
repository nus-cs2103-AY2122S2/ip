package task;

/** Abstract class for categorising different Tasks.
 *  Holds a description and the status of whether its done or not.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /** Creates new Task, with minimally a description and status */
    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getTaskStatus() {
        return this.isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns String that is formatted for a way where its written to the file.
     * @return Formatted String that represents the Task.
     */
    public abstract String fileFormat();
}
