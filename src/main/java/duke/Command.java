package duke;

import java.util.HashMap;

/**
 * Represents commands entered by the input.
 * Commands are to be processed by Parser.
 */
public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    ADD,
    DELETE,
    FIND;

    private static HashMap<String, Command> commands = new HashMap<>();

    static {
        commands.put("bye", Command.BYE);
        commands.put("list", Command.LIST);
        commands.put("mark", Command.MARK);
        commands.put("unmark", Command.UNMARK);
        commands.put("todo", Command.ADD);
        commands.put("deadline", Command.ADD);
        commands.put("event", Command.ADD);
        commands.put("delete", Command.DELETE);
        commands.put("find", Command.FIND);
    }

    /**
     * Gets command by using a string of command.
     *
     * @param c command to be processed.
     * @return Command if exist else null.
     */
    public static Command getCommand(String c) {
        // return null if command not in hashmap.
        return commands.containsKey(c)
                ? commands.get(c)
                : null;
    }

    /**
     * Check if a command is valid.
     *
     * @param c command to be processed.
     * @return true if command is valid else false.
     */
    public static boolean isValid(String c) {
        return commands.containsKey(c);
    }
}
