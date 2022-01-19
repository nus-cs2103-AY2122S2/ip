public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }

    public Todo(String content, boolean done) {
        super(content, done);
    }

    @Override
    public Todo mark(boolean done) {
        return new Todo(super.getContent(), done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
