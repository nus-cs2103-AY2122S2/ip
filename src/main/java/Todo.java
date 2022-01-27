public class Todo extends Task {
    private static final String TYPE = "T";
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String writeToFile() {
        return TYPE + " === " + super.writeToFile() + " === ";
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString();
    }

}