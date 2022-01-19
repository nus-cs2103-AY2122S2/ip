public enum ValidCommand {
    LIST("list"),
    BYE("bye");

    public final String label;
    ValidCommand(String label) {
        this.label = label;
    }
}
