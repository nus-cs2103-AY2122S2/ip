public enum Handlers {
    BYE("bye"),
    LIST("list");

    public final String label;

    private Handlers(String label) {
        this.label = label;
    }
}
