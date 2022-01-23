public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean isHasDate() {
        return false;
    }

    @Override
    public boolean isHasTime() {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
