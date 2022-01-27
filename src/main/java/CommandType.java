import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    BYE("bye", null),
    LIST("list", null),
    MARK("mark", "\\d+"),
    UNMARK("unmark", "\\d+"),
    DELETE("delete", "\\d+"),
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

    private CommandType(String commandString, String regex) {
        this.commandString = commandString;
        this.regex = regex;
    }

    public static CommandType getCommand(String commandString) throws DukeException {
        CommandType command = commandMap.get(commandString);

        if (command == null) {
            throw new DukeException("I'm sorry, you've input a command I don't recognize. Please try again.");
        }

        return command;
    }

    public String getRegex() {
        return this.regex;
    }

}
