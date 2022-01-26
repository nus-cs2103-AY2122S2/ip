package juke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    
    @Override
    public String getTaskIcon() {
        return "[T]";
    }
}
