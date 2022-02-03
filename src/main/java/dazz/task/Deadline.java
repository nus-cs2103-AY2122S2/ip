package dazz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents <code>Task</code> that has a deadline
 */
public class Deadline extends Task {
    private static final String TYPE = "D";
    private final LocalDateTime dateTime;

    /**
     * Constructs a <code>Deadline</code> task and is not completed by default.
     * @param description Description of this <code>Deadline</code>.
     * @param date Due date of this <code>Deadline</code>.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.dateTime = date;
    }

    /**
     * Constructs a <code>Deadline</code> task and is completed depending on <code>isDone</code>.
     * @param description Description of this <code>Deadline</code>.
     * @param isDone Done or undone <code>Deadline</code>.
     * @param date Due date of this <code>Deadline</code>.
     */
    public Deadline(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone);
        this.dateTime = date;
    }

    /**
     * Gets the date and time of this <code>Deadline</code>.
     * @return Date and time of this <code>Deadline</code>.
     */
    public String getDateTimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd MMM yyyy, hh:mma]");
        return this.dateTime.format(formatter);
    }

    /**
     * Reformats this <code>Deadline</code> to be stored in a text file.
     * @return Text that would be stored as in a file.
     */
    @Override
    public String writeToFile() {
        return TYPE + " === " + super.writeToFile() + " === " + this.getDateTimeFormat();
    }

    /**
     * String representation of this <code>Deadline</code>.
     * @return String representation of the this <code>Deadline</code>.
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (by: " + this.getDateTimeFormat() + ")";
    }
}
