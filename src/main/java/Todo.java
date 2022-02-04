public class Todo extends Task {
    public Todo(String content) {
        super(content);
    }

    public Todo(String content, boolean isDone) {
        super(content, isDone);
    }

    @Override
    public Todo mark(boolean isDone) {
        return new Todo(super.getContent(), isDone);
    }

    @Override
    public String fileFormat() {
        return "T" + super.fileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
