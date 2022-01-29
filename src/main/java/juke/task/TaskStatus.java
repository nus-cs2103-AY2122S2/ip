package juke.task;

/**
 * Abstraction for the status of a task.
 */
public enum TaskStatus {
    NOT_DONE("\u2610"),
    DONE("\u2612");
    
    /**
     * Status icon.
     */
    private final String statusIcon;
    
    /**
     * Constructor to include the status icon.
     *
     * @param statusIcon Status icon.
     */
    private TaskStatus(String statusIcon) {
        this.statusIcon = statusIcon;
    }
    
    /**
     * Returns the status icon of the task.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return this.statusIcon;
    }
}
