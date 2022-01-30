package spike.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulate information of task with deadline.
 */
public class Deadline extends Task {

    /**
     * Default constructor.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, by);
    }

    /**
     * Returns the representative string for saving in data file.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        int status = super.isDone ? 1 : 0;
        return "D" + " | " + status + " | " + super.description + " | " + dtf.format(super.dateTime);
    }

    /**
     * Returns proper string representation of the task object
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[D]" + super.toString() + " (by: " + dtf.format(super.dateTime) + ")";
    }
}
