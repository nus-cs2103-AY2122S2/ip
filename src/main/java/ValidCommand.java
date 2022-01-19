public enum ValidCommand {
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    BYE("bye");

    public final String label;
    ValidCommand(String label) {
        this.label = label;
    }
}
