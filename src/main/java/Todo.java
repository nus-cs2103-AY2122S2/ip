public class Todo extends Task {
    public Todo(String name) {
        this(name, false);
    }

    public Todo(String name, Boolean done) {
        super(name, done);
    }

    @Override
    public String nameWithStatus() {
        return String.format("[T]%s", super.nameWithStatus());
    }
}
