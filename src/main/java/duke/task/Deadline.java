package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represents a deadline to be fulfilled. This deadline is a task that possesses a state/status that is by default
 * not done
 */
public class Deadline extends Task {

    protected LocalDate date;
    protected String time;
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a task with a deadline, that is initially not done
     *
     * @param description A short description of the deadline
     * @param date        Date of the deadline as a String
     */
    public Deadline(String description, String date) throws DukeException {
        super(description);
        this.time = "";
        String[] dateSplit = date.split(" ", 2);
        try {
            this.date = LocalDate.parse(dateSplit[0]);
            if (dateSplit.length != 1) {
                this.time = dateSplit[1];
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date! Date format to be of type yyyy-mm-dd\n");
        }
    }

    /**
     * Returns true if time of event was given as input
     *
     * @return true if deadline has an event time
     */
    private boolean hasTime() {
        return !this.time.equalsIgnoreCase("");
    }

    /**
     * Returns the String representation of a Deadline task
     *
     * @return String format of a Deadline
     */
    @Override
    public String toString() {
        StringBuilder eventTime = new StringBuilder();
        if (hasTime()) {
            eventTime.append(" ").append(time);
        }
        return "[D]" + super.toString() + " (by: " + date.format(pattern) + eventTime + ")";
    }

    /**
     * Returns the String format of how the Deadline will be saved in the text file
     *
     * @return String format of deadline task to be saved
     */
    public String toSave() {
        return String.format("D | %d | %s | %s | %s", this.isDone ? 1 : 0, this.description, this.date, this.time);
    }
}
