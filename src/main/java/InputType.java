public enum InputType {
    BYE("bye"),
    LIST("list"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    MARK("mark"),
    UNMARK("unmark"),
    NONE("none");

    public final String label;

    private InputType(String label) {
        this.label = label;
    }

}
