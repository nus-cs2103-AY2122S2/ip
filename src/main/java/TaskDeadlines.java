import java.time.LocalDate;

public class TaskDeadlines extends Task {
    LocalDate doneBy;

    /**
     * Constructor
     */
    public TaskDeadlines(boolean isDone, String name, String doneBy) {
        super(isDone, name);
        this.doneBy = LocalDate.parse(doneBy);
    }

    /**
     * Returns String representation of the Task
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X" : " ", name, doneBy.toString());
    }
}
