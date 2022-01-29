package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    /**
     * A Deadline constructor to initialise a <code>Deadline</code> object. A <code>Deadline</code>
     * corresponds to a task represented by a String, LocalDate, LocalTime.
     * E.g., <code>do project, 12-12-2022, 1900</code>.
     *
     * @param description the description of the deadline task to be done.
     * @param date the date of the deadline of the task.
     * @param time the time of the deadline of the task.
     */
    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns the string representation of the <code>Deadline</code> task.
     *
     * @return the string representation of the <code>Deadline</code> task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
    }
}
