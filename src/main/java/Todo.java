public class Todo extends Task {
    private String type;

    public Todo(String description) {
        super(description);
        this.type = "T";
    }

    @Override
    public String getTask() {
        return "[" + this.type +"]" + super.getTask();
    }

    @Override
    public String getDescription() {
        return this.type + " | " + this.description;
    }
}