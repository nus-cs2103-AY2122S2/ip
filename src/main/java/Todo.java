import java.time.format.DateTimeParseException;

public class Todo extends Task {
    protected boolean status;

    public Todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    public Todo(String description) throws DateTimeParseException {
        this(false, description);
    }

    @Override
    public String appendtoFile() {
        return "T|" + (super.isDone ? "1" : "0") + "|" + super.description + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
