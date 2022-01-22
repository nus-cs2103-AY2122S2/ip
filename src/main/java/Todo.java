public class Todo extends Task {

    protected String icon = "T";

    public Todo(String description) {
        super(description);
    }
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString();
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Todo mark() { return new Todo(description, true); }

    @Override
    public Todo unmark() { return new Todo(description, false); }

}
