import java.util.Locale;

public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    EVENT("event"),
    DEADLINE("deadline"),
    TODO("todo"),
    UNRECOGNISED("unrecognised");

    private String commandKeyword;

    Command(String commandKeyword) {
        this.commandKeyword = commandKeyword;
    }

    public static Command getCommand(String commandKeyword) {
        Command command;

        try {
            command = Command.valueOf(commandKeyword.toUpperCase());
        } catch (IllegalArgumentException e) {
            command = Command.UNRECOGNISED;
        }

        return command;
    }

    public String getCommandKeyword() {
        return this.commandKeyword;
    }
}
