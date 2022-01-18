public class Todo extends Task {
    private static final String tag = "T";

    public Todo(String name) {
        this(name, false);
    }

    public Todo(String name, Boolean done) {
        super(name, done);
    }

    @Override
    public String nameWithStatus() {
        return String.format("[%c][%c] %s",
                this.tag,
                super.isDone(),
                super.getName());
    }
}
