package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of task that contains due date.
 */
public class Deadline extends Task {

    protected Boolean isLocalDate = false;
    protected LocalDate by;
    protected String strBy;

    /**
     * Creates a new Deadline class.
     *
     * @param description string of the description.
     * @param strBy string of the due date.
     */
    public Deadline(String description, String strBy) {
        super(description);
        strBy = strBy.trim();
        try {
            this.by = LocalDate.parse(strBy);
            isLocalDate = true;
        } catch (DateTimeParseException e) {
            this.strBy = strBy;
        }
    }

    /**
     * Returns string format for the save file.
     *
     * @return string format for the save file.
     */
    @Override
    public String saveString() {
        return "D" + "|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + by;
    }

    /**
     * Returns string format for the printing.
     *
     * @return string format for the printing.
     */
    @Override
    public String toString() {
        if (isLocalDate) {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + strBy + ")";
        }
    }
}
