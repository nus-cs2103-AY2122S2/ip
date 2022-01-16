public enum Handlers {
    BYE("bye"),
    MARK("mark"),
    LIST("list"),
    UNMARK("unmark");

    public final String label;

    private Handlers(String label) {
        this.label = label;
    }
}
