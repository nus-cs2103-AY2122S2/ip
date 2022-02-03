package juke.task;

/**
 * Enum for the status of a task.
 */
public enum TaskStatus {
    NOT_DONE("unmark", "\u2610"),
    DONE("mark", "\u2612");
    
    /**
     * String for command use.
     */
    private final String commandName;
    
    /**
     * Status icon.
     */
    private final String statusIcon;
    
    /**
     * Constructor to initialize the task statuses.
     *
     * @param commandName Command name.
     * @param statusIcon Status icon.
     */
    TaskStatus(String commandName, String statusIcon) {
        this.commandName = commandName;
        this.statusIcon = statusIcon;
    }
    
    /**
     * Returns the command name.
     *
     * @return Command name.
     */
    public String getCommandName() {
        return commandName;
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
