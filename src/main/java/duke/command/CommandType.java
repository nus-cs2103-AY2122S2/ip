package duke.command;

/**
 * An enumeration that defines different command types.
 */
public enum CommandType {
    BYE("bye"),
    HELP("help"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    PRINT("print"),
    FIND("find");

    private final String label;

    /**
     * Constructor to initialize an instance of CommandType enumeration
     * with label field.
     *
     * @param label Label of the command type
     */
    CommandType(String label) {
        this.label = label;
    }

    /**
     * Returns the label of the command type.
     *
     * @return The label of the command type
     */
    public String getLabel() {
        return label;
    }
}
