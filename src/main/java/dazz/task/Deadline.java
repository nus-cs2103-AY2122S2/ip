package dazz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline
 */
public class Deadline extends Task {
    private static final String TYPE = "D";
    private final LocalDateTime dateTime;

    /**
     * Constructs a <code>Deadline</code> task and is not completed by default.
     * @param description Description of the task.
     * @param date Due date of the task.
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.dateTime = date;
    }

    /**
     * Constructs a <code>Deadline</code> task and is completed depending on <code>isDone</code>.
     * @param description Description of the task.
     * @param isDone Done or undone task.
     * @param date Due date of the task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone);
        this.dateTime = date;
    }

    /**
     * Gets the date and time of the task.
     * @return Date and time of the task.
     */
    public String getDateTimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd MMM yyyy, hh:mma]");
        return this.dateTime.format(formatter);
    }

    /**
     * Reformats the task to be stored in a text file.
     * @return Text that would be stored as in a file.
     */
    @Override
    public String writeToFile() {
        return TYPE + " === " + super.writeToFile() + " === " + this.getDateTimeFormat();
    }

    /**
     * String representation of the deadline task.
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (by: " + this.getDateTimeFormat() + ")";
    }
}