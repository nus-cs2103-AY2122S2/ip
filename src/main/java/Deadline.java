import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents tasks that need to be done
 * before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Class constructor specifying the task's description
     * and deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String formatForFile() {
        return "D | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + this.by + "\n";
    }

    /**
     * Returns type and description of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}