package jose.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jose.DukeException;

/**
 * A class representing a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor that sets isDone to false.
     *
     * @param description Task description.
     * @param by Task deadline.
     * @throws DukeException If date and time are in the wrong format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrecto date/time format. Use yyyy-MM-dd HHmm eg. 2022-12-31 2359");
        }
    }

    /**
     * Constructor that sets all variables.
     *
     * @param description Task description.
     * @param isDone Task status.
     * @param by Task deadline.
     * @param priority Task priority.
     */
    public Deadline(String description, boolean isDone, String by, Priority priority) {
        super(description, isDone, priority);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Returns a formatted representation of a task that will be saved to the data file.
     *
     * @return A formatted string to be saved to the data file.
     */
    public String formatData() {
        return "D|" + super.formatData() + "|" + by;
    }

    /**
     * Returns a string representation of a task.
     *
     * @return A string representation of a task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}
