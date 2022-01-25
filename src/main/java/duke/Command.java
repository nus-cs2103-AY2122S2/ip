package duke;

import java.util.HashMap;

public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE;

    private static HashMap<String, Command> commands = new HashMap<>();

    static {
        commands.put("bye", Command.BYE);
        commands.put("list", Command.LIST);
        commands.put("mark", Command.MARK);
        commands.put("unmark", Command.UNMARK);
        commands.put("todo", Command.TODO);
        commands.put("deadline", Command.DEADLINE);
        commands.put("event", Command.EVENT);
        commands.put("delete", Command.DELETE);
    }

    public static Command getCommand(String c) {
        // return null if command not in hashmap.
        return commands.containsKey(c)
                ? commands.get(c)
                : null;
    }

    public static boolean isValid(String c) {
        return commands.containsKey(c);
    }
}
