package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * a type of task that contains due date
 */
public class Deadline extends Task {

    protected Boolean useLocalDate = false;
    protected LocalDate by;
    protected String strBy;

    /**
     * Create a new Deadline class
     *
     * @param description string of the description
     * @param strBy string of the due date
     */
    public Deadline(String description, String strBy) {
        super(description);
        strBy = strBy.trim();
        try {
            this.by = LocalDate.parse(strBy);
            useLocalDate = true;
        } catch (DateTimeParseException e) {
            this.strBy = strBy;
        }
    }

    /**
     * string format for the save file
     *
     * @return string format for the save file
     */
    @Override
    public String saveString() {
        return "D" + "|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + by;
    }

    /**
     * string format for the save file
     *
     * @return string format for the save file
     */
    @Override
    public String toString() {
        if (useLocalDate) {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + strBy + ")";
        }
    }
}
