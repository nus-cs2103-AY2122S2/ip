public enum Handlers {
    Bye("bye"),
    Deadline("deadline"),
    Delete("delete"),
    Event("event"),
    Mark("mark"),
    List("list"),
    Todo("todo"),
    Unmark("unmark");

    public final String label;

    private Handlers(String label) {
        this.label = label;
    }
}
