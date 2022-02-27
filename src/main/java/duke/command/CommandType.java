package duke.command;
import java.util.HashMap;
import java.util.Map;

import duke.DukeException;

/**
 * An Enum class to represent the types of commands a user can perform.
 */
public enum CommandType {
    BYE("bye", null),
    LIST("list", null),
    MARK("mark", "\\d+"),
    UNMARK("unmark", "\\d+"),
    DELETE("delete", "\\d+"),
    FIND("find", null),
    TODO("todo", null),
    EVENT("event", "/at"),
    DEADLINE("deadline", "/by");

    private static final Map<String, CommandType> commandMap = new HashMap<>();

    static {
        for (CommandType c : values()) {
            commandMap.put(c.commandString, c);
        }
    }

    private final String commandString;
    private final String regex;

    /**
     * Initialize the CommandType Enum, by passing in the user input String.
     * @param commandString String representing the user's input.
     * @param regex The regex associated with the CommandType.
     */
    private CommandType(String commandString, String regex) {
        this.commandString = commandString;
        this.regex = regex;
    }

    /**
     * Method to retrieve the CommandType Enum associated with the user's input.
     * @param commandString User input String
     * @return Enum representing the CommandType.
     * @throws DukeException Error if the user input String is an unrecognized command.
     */
    public static CommandType getCommand(String commandString) throws DukeException {
        CommandType command = commandMap.get(commandString);

        if (command == null) {
            throw new DukeException("I'm sorry, you've input a command I don't recognize. Please try again.");
        }

        return command;
    }

    /**
     * Returns the regex associated with this CommandType Enum.
     * @return The regex associated with this CommandType Enum.
     */
    public String getRegex() {
        return this.regex;
    }

}
