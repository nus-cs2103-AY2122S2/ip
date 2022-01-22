import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A deadline task that has a deadline property.
 */
public class DeadlineTask extends Task {
    /**
     * The deadline of the task, in yyyy-mm-dd format
     */
    private LocalDate deadline;

    /**
     * The deadline of the task parsed in MMM dd yy format.
     */
    private String deadlineString;

    /**
     * Constructs a new deadline task from the description and the
     * deadline. Accepts date in the format of yyyy-mm-dd;
     *
     * @param description description of the task
     * @param deadline deadline of the task
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
        this.deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return the string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + this.deadlineString + ")";
    }
}
