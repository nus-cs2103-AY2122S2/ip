package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Represents a Deadline task which has to be completed by a specific time */
public class Deadline extends Task {
    protected LocalDate time;

    /**
     * Creates a new instance of a Deadline.
     * Assumes the task is not done yet.
     *
     * @param name The content of the task.
     * @param time The time of the task in YYYY-MM-DD format.
     * @throws DateTimeParseException If the given time is not in the YYYY-MM-DD format.
     */
    public Deadline(String name, String time) throws DateTimeParseException {
        this(name, time, false);
    }

    /**
     * Creates a new instance of a Deadline.
     *
     * @param name The content of the task.
     * @param time The time of the task in YYYY-MM-DD format.
     * @param isDone Whether the task is done yet.
     * @throws DateTimeParseException If the given time is not in the YYYY-MM-DD format.
     */
    public Deadline(String name, String time, boolean isDone) throws DateTimeParseException {
        this(name, LocalDate.parse(time), isDone);
    }

    /**
     * Creates a new instance of a Deadline.
     *
     * @param name The content of the task.
     * @param time The time of the task.
     * @param isDone Whether the task is done yet.
     */
    public Deadline(String name, LocalDate time, boolean isDone) {
        super(name, "D", isDone);
        this.time = time;
    }

    /**
     * Returns a String representation of the Deadline.
     * Indicates the task type and whether it has been done.
     * Also indicates the time of the task.
     *
     * @return A String representation of the Deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + formatTime() + ")";
    }

    /**
     * Formats the time in MMM dd yyyy format.
     *
     * @return The time in MMM dd yyyy format.
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
