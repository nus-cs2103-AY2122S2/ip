package duke.task;

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
    public String getParsedDateAndTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(this.dateAndTime);
            return localDateTime.format(dateTimeFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date/Time format!");
        }
        return LocalDateTime.now().format(dateTimeFormatter);
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
        return "[D]" + this.getStatusIcon() + " " + super.description + " (by: " + this.getParsedDateAndTime() + ")";
    }
}
