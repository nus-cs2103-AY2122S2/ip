package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that is required to be done before a specific
 * date/time.
 */
public class Deadline extends Task {

    /**
     * String representation of the deadline.
     */
    protected LocalDateTime by;

    /**
     * Constructor to create a deadline task.
     *
     * @param description text description of the deadline.
     * @param by date/time deadline is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description, TaskType.DEADLINE);
        this.by = by;
    }


    /**
     * Outputs the deadline of the task as formatted string.
     *
     * @return deadline as a string.
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("h:mm a")) + " "
                + by.format(DateTimeFormatter.ofPattern("MMMM d yyyy"));
    }

    /**
     * Outputs the string of deadline using LocalDateTime that
     * can be parsed by Java.
     *
     * @return deadline as string convenient to be parsed.
     */
    public String byToString() {
        return by.toString();
    }

    /**
     * Outputs the storage string/format for the task.
     *
     * @return string formatted for storage.
     */
    public String getStorageString() {
        return "D | " + (getIsDone() ? "1" : "0") + " | " + getDescription()
                + " | " + byToString() + "\n";
    }

    /**
     * Outputs the string to represent deadline with
     * description and due date/time.
     *
     * @return formatted string representing the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }
}