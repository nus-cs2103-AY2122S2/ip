package duke.command;

import duke.DukeException;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    ADD_TODO("todo", "desc"),
    ADD_DEADLINE("deadline", "desc", "by"),
    ADD_EVENT("event", "desc", "at", "dur"),
    DELETE_TASK("delete", "id"),
    MARK_TASK("mark", "id"),
    UNMARK_TASK("unmark", "id"),
    EXIT("bye"),
    LIST("list"),
    FIND("find", "key");

    private static final Map<String, CommandType> typeMap = new HashMap<>();

    static {
        for (CommandType c : values()) {
            typeMap.put(c.command, c);
        }
    }

    private final String command;
    private final String[] params;

    CommandType(String command, String... params) {
        this.command = command;
        this.params = params;
    }

    public static CommandType fromString(String commandString) {
        CommandType c = typeMap.get(commandString);

        if (c == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return c;
    }

    public String[] getParams() {
        return params;
    }
}
