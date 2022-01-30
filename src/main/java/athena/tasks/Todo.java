package athena.tasks;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getIcon() {
        return "T";
    }
}
