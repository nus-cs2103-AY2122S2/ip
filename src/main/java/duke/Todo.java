package duke;
public class Todo extends Task {
    // attributes
    protected String type;

    // constructor
    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
