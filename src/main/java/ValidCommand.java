public enum ValidCommand {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    BYE("bye");

    public final String label;
    ValidCommand(String label) {
        this.label = label;
    }
}
