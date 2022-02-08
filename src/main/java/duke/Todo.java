package duke;

public class Todo extends Task {
        
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Task mark() {
        return new Todo(this.description, true);
    }
    
    @Override
    public Task unmark() {
        return new Todo(this.description, false);
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
