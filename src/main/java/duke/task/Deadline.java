package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected String dateAndTime;

    /**
     * Constructor for a Deadline object.
     *
     * @param description the description of the task.
     * @param dateAndTime the date and time of the deadline.
     */
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime.strip();
    }

    /**
     * Returns the parsed date and time that the user inputted.
     *
     * @return the parsed date and time.
     */
    public String getParsedDateAndTime() throws DukeException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(this.dateAndTime);
            return localDateTime.format(dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date/Time format!");
        }
        throw new DukeException("Invalid Date/Time format! Please use /by YYYY-MM-DDTHH:mm:ss format");
    }

    /**
     * Returns the date and time the user inputted.
     *
     * @return the date and time.
     */
    public String getDateAndTime() {
        return this.dateAndTime;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return a string representing the task.
     */
    @Override
    public String toString() {
        try {
            return "[D]" + this.getStatusIcon() + " " + super.description + " (by: " + this.getParsedDateAndTime() + ")";
        } catch (DukeException e) {
            System.out.println("Task date format is invalid.");
        }
        return "[D]" + this.getStatusIcon() + " " + super.description;
    }
}
