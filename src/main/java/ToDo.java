public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    ToDo(String description, Boolean completed) {
        super(description, completed);
    }

    @Override
    public String getType() {
        return "ToDo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
