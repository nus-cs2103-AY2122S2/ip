package duke.task;

public class Todo extends Task {
    private String type;

    public Todo(String description) {
        super(description);
        type = "T";
    }

    @Override
    public String getTask() {
        return "[" + type +"]" + super.getTask();
    }

    @Override
    public String getDescription() {
        return type + " | " + description;
    }
}