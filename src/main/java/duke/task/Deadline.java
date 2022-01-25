package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class represent for a deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Class constructor with the description and the date time following yyyy-MM-dd format
     * Creates an undone Deadline.
     *
     * @param description Deadline description
     * @param by Deadline date time
     * @throws DateTimeException error when wrong format date time
     */
    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        this.by = LocalDate.parse(by.strip());
    }

    /**
     * Returns the date of Deadline
     * @return Deadline date in yyyy-mm-dd format
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Overrides toString method to make a string including prefix, status icon, description and date.
     * @return String representation of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
