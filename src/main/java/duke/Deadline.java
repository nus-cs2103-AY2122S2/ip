package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for Deadline, which extends the Task class
 *
 * @author Benjamin Koh
 */

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for a new instance of Deadline, which entails the name of the Deadline, and the Date & Time of the
     * Deadline
     *
     * @param taskName Name of the deadline
     * @param by Date & Time of the deadline
     */

    public Deadline(String taskName, String by) {
        super(taskName);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, format);
    }

    /**
     * Return a string with the Task type, the isDone status, the Deadline name, and Date & Time of deadline
     *
     * @return a string with the Task type, the isDone status, the Deadline name, and Date & Time of deadline
     */

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(displayFormat) + ")";
    }
}
