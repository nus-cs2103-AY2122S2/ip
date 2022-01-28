package duke.task;
public class Todo extends Task {
    
    public Todo (String description, boolean done) {
        super(description, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStringSaveData() {
        return String.join(" | ", "T", String.valueOf(done ? 1 : 0), description);
    }

}
