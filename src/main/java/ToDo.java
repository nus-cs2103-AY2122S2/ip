public class ToDo extends Task{
    public ToDo(String description) {
        super(description, false);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String formatData() {
        return "T|" + super.formatData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
