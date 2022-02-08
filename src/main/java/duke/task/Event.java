package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Represents an Event task which takes place at a specific time */
public class Event extends Task {
    protected LocalDate time;

    /**
     * Creates a new instance of an Event.
     * Assumes the task is not done yet.
     *
     * @param name The content of the task.
     * @param time The time of the task in YYYY-MM-DD format.
     * @throws DateTimeParseException If the given time is not in the YYYY-MM-DD format.
     */
    public Event(String name, String time) throws DateTimeParseException {
        this(name, time, false);
    }

    /**
     * Creates a new instance of an Event.
     *
     * @param name The content of the task.
     * @param time The time of the task in YYYY-MM-DD format.
     * @param isDone Whether the task is done yet.
     * @throws DateTimeParseException If the given time is not in the YYYY-MM-DD format.
     */
    public Event(String name, String time, boolean isDone) throws DateTimeParseException {
        this(name, LocalDate.parse(time), isDone);
    }

    /**
     * Creates a new instance of an Event.
     *
     * @param name The content of the task.
     * @param time The time of the task.
     * @param isDone Whether the task is done yet.
     */
    public Event(String name, LocalDate time, boolean isDone) {
        super(name, "E", isDone);
        this.time = time;
    }

    /**
     * Returns a String representation of the Event.
     * Indicates the task type and whether it has been done.
     * Also indicates the time of the task.
     *
     * @return A String representation of the Event.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: "
                + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a String representation of the Event to be entered in the storage file.
     *
     * @return A String representation of the Event.
     */
    @Override
    public String toFileFormatString() {
        if (isDone) {
            return super.toFileFormatString() + " | " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return super.toFileFormatString() + " | " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
