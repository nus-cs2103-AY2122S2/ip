package duke.commands;

/**
 * Valid commands.
 */
public enum CommandType {
    /**
     * Bye command.
     */
    BYE("bye"),

    /**
     * List command.
     */
    LIST("list"),

    /**
     * Mark command.
     */
    MARK("mark"),

    /**
     * Unmark command.
     */
    UNMARK("unmark"),

    /**
     * Todo command.
     */
    TODO("todo"),

    /**
     * Deadline command.
     */
    DEADLINE("deadline"),

    /**
     * Event command.
     */
    EVENT("event"),

    /**
     * Find command.
     */
    FIND("find"),

    /**
     * Delete command.
     */
    DELETE("delete");

    /**
     * Command string.
     */
    private final String command;

    /**
     * Constructor for the Command enum.
     *
     * @param command command string
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * Retrieve type
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Checks that the given command is a valid command.
     *
     * @param command command to be validated
     * @return boolean, whether the command is valid or not
     */
    public static boolean isValidCommand(String command) {
        for (CommandType e : CommandType.values()) {
            if (e.command.equals(command)) {
                return true;
            }
        }
        return false;
    }
}
