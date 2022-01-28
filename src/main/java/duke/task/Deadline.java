package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a subclass of Task. It is a more specific Task that is a reminder of an important
 * due date with its date attached.
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Deadline constructor.
     * @param details is a String containing information on what the deadline is.
     * @param date is a LocalDate object of the date the deadline is due.
     */
    public Deadline(String details, LocalDate date) {
        super(details);
        this.date = date;
    }

    /**
     * Passes the Deadline object out in readable format.
     * @return the readable string giving the Deadline's details.
     */
    @Override
    public String toString() {
        return "D" + super.toString() + " | " + this.date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));

    }

}
