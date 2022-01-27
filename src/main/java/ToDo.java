public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String toInputString() {
        return "todo " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
