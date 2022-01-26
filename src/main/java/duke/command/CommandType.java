package duke.command;

import duke.DukeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the possible Command types.
 */
public enum CommandType {
    ADD_TODO("todo", "desc"),
    ADD_DEADLINE("deadline", "desc", "by"),
    ADD_EVENT("event", "desc", "at", "dur"),
    DELETE_TASK("delete", "id"),
    MARK_TASK("mark", "id"),
    UNMARK_TASK("unmark", "id"),
    EXIT("bye"),
    LIST("list");

    private static final Map<String, CommandType> typeMap = new HashMap<>();

    static {
        for (CommandType type : values()) {
            typeMap.put(type.command, type);
        }
    }

    private final String command;
    private final String[] params;

    CommandType(String command, String... params) {
        this.command = command;
        this.params = params;
    }

    /**
     * Maps a String to a CommandType.
     *
     * @param commandString The String to be mapped.
     * @return The CommandType associated with the String.
     */
    public static CommandType fromString(String commandString) {
        CommandType type = typeMap.get(commandString);

        if (type == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return type;
    }

    public String[] getParams() {
        return params;
    }
}
