package lily.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Task which records the time when it is due as a LocalDate.
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Create a Deadline object.
     * 
     * @param s The description of what is due.
     * @param by When the task is due, in the format of "yyyy-mm-dd".
     * @throws DateTimeParseException When the input is not a date.
     */
    public Deadline(String s, String by) throws DateTimeParseException {
        super(s);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the Deadline as a String 
     * 
     * @return Deadline in the form of "[D][ ] Description (by: due)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
        + by.format(DateTimeFormatter.ofPattern("dd MMM yy")) + ")";
    }
} 