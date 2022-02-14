package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadline a user would create. A <code>Deadline</code> object is a subclass of the Task class
 * and corresponds to a deadline input by a user.
 */
public class Deadline extends Task {
    protected LocalTime time;
    protected LocalDate date;

    /**
     * Constructor for the Deadline class.
     * @param description information about the deadline.
     * @param date date details about the deadline.
     * @param time time details about the deadline.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.time = time;
        this.date = date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns the string representation of a deadline.
     * @return a string representation of the deadline, consisting of its description
     * and formatted date and time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }

}
