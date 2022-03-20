package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task which has to be completed by a specific time.
 */
public class Deadline extends Task {
    protected LocalDate time;

    /**
     * Creates a new instance of a Deadline.
     * Assumes the Task is not done yet.
     *
     * @param name The content of the Task.
     * @param time The deadline of the Task in YYYY-MM-DD format.
     * @throws DateTimeParseException If the given deadline is not in the YYYY-MM-DD format.
     */
    public Deadline(String name, String time) throws DateTimeParseException {
        this(name, time, false);
    }

    /**
     * Creates a new instance of a Deadline.
     *
     * @param name The content of the Task.
     * @param time The deadline of the Task in YYYY-MM-DD format.
     * @param isDone Whether the Task is done yet.
     * @throws DateTimeParseException If the given deadline is not in the YYYY-MM-DD format.
     */
    public Deadline(String name, String time, boolean isDone) throws DateTimeParseException {
        this(name, LocalDate.parse(time), isDone);
    }

    /**
     * Creates a new instance of a Deadline.
     *
     * @param name The content of the Task.
     * @param time The time of the Task.
     * @param isDone Whether the Task is done yet.
     */
    public Deadline(String name, LocalDate time, boolean isDone) {
        super(name, "D", isDone);
        this.time = time;
    }

    /**
     * Returns a String representation of the Deadline.
     * Indicates the task type and whether it has been done.
     * Also indicates the deadline of the Task.
     *
     * @return A String representation of the Deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + formatTime() + ")";
    }

    /**
     * Formats the deadline in MMM dd yyyy format.
     *
     * @return The deadline in MMM dd yyyy format.
     */
    public String formatTime() {
        return time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns a String representation of the Deadline to be entered in the storage file.
     *
     * @return A String representation of the Deadline.
     */
    @Override
    public String toFileFormatString() {
        if (isDone) {
            return super.toFileFormatString() + " | " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return super.toFileFormatString() + " | " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
