package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * A type of task which contains the description and date of the task.
 */
public class Deadline extends Task {
    protected LocalDate dueDate;
    protected LocalTime time;
    protected String date;

    /**
     * Constructor of this Deadline class.
     *
     * @param description description of the task
     * @param dueDate date and time of the event
     * @throws DukeException an exception when format of date is inputted wrong
     */
    public Deadline(String description, String dueDate) throws DukeException {
        super(description);
        this.date = dueDate;
        try {
            this.setDueDateTime(dueDate);
        } catch (Exception e) {
            throw new DukeException("Wrong Date and Time format. yyyy-mm-dd HH:MM\n");
        }
    }

    /**
     * Formats date and time.
     *
     * @param dueDate the date and time of event
     */
    private void setDueDateTime(String dueDate) {
        this.dueDate = LocalDate.parse(dueDate.split(" ")[0]);
        this.time = LocalTime.parse(dueDate.split(" ")[1]);
    }

    /**
     * Convert date into a string representation.
     *
     * @return a string form of the date
     */
    private String dateToString() {
        return this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Convert time into a string representation.
     *
     * @return a string form of the time
     */
    private String timeToString() {
        return this.time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * Obtain date of event.
     *
     * @return the String of the date
     */
    public String getDate() {
        return this.dateToString();
    }

    /**
     * Get unformatted data of the event for storage purposes.
     *
     * @return string of unformatted data
     */
    @Override
    public String getTaskData() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    /**
     * Get formatted data of the event for response purposes.
     *
     * @return string of formatted data
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateToString() + " " + this.timeToString() + ")";
    }
}
