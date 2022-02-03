package juke.task;

/**
 * Enum for task types.
 */
public enum TaskType {
    TODO("todo", "[T]"),
    EVENT("event", "[E]"),
    DEADLINE("deadline", "[D]");
    
    /**
     * String for command use.
     */
    private final String commandName;
    
    /**
     * Task icon.
     */
    private final String taskIcon;
    
    /**
     * Constructor to initialize class types.
     *
     * @param commandName Command name.
     * @param taskIcon Task icon.
     */
    TaskType(String commandName, String taskIcon) {
        this.commandName = commandName;
        this.taskIcon = taskIcon;
    }
    
    /**
     * Returns the command name.
     *
     * @return Command name.
     */
    public String getCommandName() {
        return this.commandName;
    }
    
    /**
     * Returns the task icon of the given task type.
     *
     * @return Task icon.
     */
    public String getTaskIcon() {
        return this.taskIcon;
    }
}
