import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Represent the deadline of task.
 */

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for Deadline.
     *
     * @param description description of the task.
     * @param by by when the deadline end.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.date = super.getTaskDate(by);
        this.time = super.getTaskTime(by);

    }

    /**
     * String representation of Deadline.
     *
     * @return [D] with the status and description of the task,
     *         and by when.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                +  " " + time + ")";
    }
}

