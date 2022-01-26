public class Todo extends Task {
    protected String by;
    char type;

    public Todo(String description) {
        super(description);
        this.type = 't';
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
