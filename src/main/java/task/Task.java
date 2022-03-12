package task;

/** Abstract class for categorising different Tasks.
 *  Holds a description and the status of whether its done or not.
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    private Integer priorityLevel;

    /** Creates new Task, with minimally a description and status and priority level. */
    protected Task (String description, boolean isDone, Integer priorityLevel) {
        this.description = description;
        this.isDone = isDone;
        this.priorityLevel = priorityLevel;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getTaskStatus() {
        return this.isDone;
    }

    public Integer getPriorityLevel() {
        return this.priorityLevel;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns String that is formatted for a way where it's written to the file.
     * @return Formatted String that represents the Task.
     */
    public abstract String fileFormat();
}
