import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A event task that has a deadline property.
 */
public class EventTask extends Task {
    /**
     * The deadline of the task.
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
    public EventTask(String description, String deadline, boolean done) {
        super(description, done);
        this.deadline = LocalDate.parse(deadline);
        this.deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public EventTask(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
        this.deadlineString = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return the string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(by: " + this.deadlineString + ")";
    }
}
