/**
 * Valid commands.
 */
public enum Command {
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
    Command(String command) {
        this.command = command;
    }

    /**
     * Checks that the given command is a valid command.
     *
     * @param command command to be validated
     * @return boolean, whether the command is valid or not
     */
    public static boolean isValidCommand(String command) {
        for (Command e : values()) {
            if (e.command.equals(command)) {
                return true;
            }
        }
        return false;
    }
}
