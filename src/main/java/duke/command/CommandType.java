package duke.command;

/**
 * Represents the list of commands available to the Duke application.
 *
 *  @author Zheng Teck
 *  @version 1.0
 */
public enum CommandType
{
    BYE("bye"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DONE("done"),
    DELETE("delete"),
    FIND("find"),
    HELP("help"),
    UNDO("undo");

    private String command;

    CommandType(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return command;
    }
}