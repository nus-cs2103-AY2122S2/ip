package juke.task;

/**
 * Enum for task types.
 */
public enum TaskType {
    TODO("todo", "[T]", "T"),
    EVENT("event", "[E]", "E"),
    DEADLINE("deadline", "[D]", "D");

    /**
     * String for command use.
     */
    private final String commandName;

    /**
     * Task icon.
     */
    private final String taskIcon;

    /**
     * Short string to represent task type.
     */
    private final String taskShortString;

    /**
     * Constructor to initialize class types.
     *
     * @param commandName Command name.
     * @param taskIcon Task icon.
     * @param taskShortString Task short string.
     */
    TaskType(String commandName, String taskIcon, String taskShortString) {
        this.commandName = commandName;
        this.taskIcon = taskIcon;
        this.taskShortString = taskShortString;
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
     * Returns the task icon of the given task type.
     *
     * @return Task icon.
     */
    public String getTaskIcon() {
        return taskIcon;
    }

    /**
     * Returns the short string associated with the given task type.
     *
     * @return Task short string.
     */
    public String getTaskShortString() {
        return taskShortString;
    }

    /**
     * Returns the task type associated with the short string.
     *
     * @param string Short string
     * @return Task type.
     */
    public static TaskType getTaskTypeFromShortString(String string) {
        TaskType result = null;
        for (TaskType type : TaskType.values()) {
            if (string.equals(type.taskShortString)) {
                result = type;
                break;
            }
        }
        return result;
    }
}
