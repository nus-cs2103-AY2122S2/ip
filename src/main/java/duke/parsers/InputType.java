package duke.parsers;

/**
 * Defines the types of inputs as well as the labels representing it.
 */
public enum InputType {
    BYE("bye"),
    LIST("list"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    NONE("none");

    public final String label;

    private InputType(String label) {
        this.label = label;
    }

}
