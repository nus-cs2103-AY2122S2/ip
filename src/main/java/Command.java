import java.util.HashMap;

public enum Command {
    BYE,
    LIST,
    MARK,
    UNMARK,
    ADD;

    private static HashMap<String, Command> commands = new HashMap<>();

    static {
        commands.put("bye", Command.BYE);
        commands.put("list", Command.LIST);
        commands.put("mark", Command.MARK);
        commands.put("unmark", Command.UNMARK);
    }

    public static Command getCommand(String c) {
        return commands.containsKey(c)
                ? commands.get(c)
                : Command.ADD;
    }
}
