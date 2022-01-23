import java.util.HashMap;
import java.util.Map;

public enum Command {
    BYE("bye", null),
    LIST("list", null),
    MARK("mark", "//d+"),
    UNMARK("unmark", "//d+"),
    DELETE("delete", "//d+"),
    TODO("todo", null),
    EVENT("event", "/at"),
    DEADLINE("deadline", "/by");

    private static final Map<String, Command> commandMap = new HashMap<>();

    static {
        for (Command c : values()) {
            commandMap.put(c.commandString, c);
        }
    }

    private final String commandString;
    private final String regex;

    private Command(String commandString, String regex) {
        this.commandString = commandString;
        this.regex = regex;
    }

    public Command getCommand(String commandString) {
        return commandMap.get(commandString);
    }

    public String getRegex() {
        return this.regex;
    }

}
