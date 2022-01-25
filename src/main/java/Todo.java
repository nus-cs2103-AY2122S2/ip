public class Todo extends Task {
    // attributes
    protected String type;
    protected String by;

    // constructor
    public Todo(String description) {
        super(description);
        this.type = "T";
        this.by = "";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
