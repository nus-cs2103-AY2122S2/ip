import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    ADD_TODO("todo", null, "description"),
    ADD_DEADLINE("deadline", " /by ", "description", "by"),
    ADD_EVENT("event", " /at ", "description", "at"),
    DELETE_TASK("delete", null, "index"),
    MARK_TASK("mark", null, "index"),
    UNMARK_TASK("unmark", null, "index"),
    EXIT("bye", null),
    LIST("list", null);

    private static final Map<String, CommandType> typeMap = new HashMap<>();

    static {
        for (CommandType c : values()) {
            typeMap.put(c.command, c);
        }
    }

    private final String command;
    private final String regex;
    private final String[] args;

    CommandType(String command, String regex, String... args) {
        this.command = command;
        this.regex = regex;
        this.args = args;
    }

    public static CommandType fromString(String commandString) {
        CommandType c = typeMap.get(commandString);

        if (c == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return c;
    }

    public String[] getArgs() {
        return args;
    }

    public String getRegex() {
        return regex;
    }
}
