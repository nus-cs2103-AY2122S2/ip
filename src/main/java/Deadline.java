import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents a task with deadline, is a subclass of task.
 */
public class Deadline extends Task{
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("[d/M/y HHmm]"));
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description,isDone);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("[d/M/y HHmm]"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d y - HHmm")) +")";
    }
}
