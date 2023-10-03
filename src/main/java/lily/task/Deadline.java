package lily.task;

import lily.LilyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Task which records the time when it is due as a LocalDate.
 * 
 * @author Hong Yi En, Ian
 * @version Feb 2022 (AY21/22 Sem 2)
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a Deadline object.
     * 
     * @param s The description of what is due.
     * @param by When the task is due, in the format of "yyyy-mm-dd".
     * @throws LilyException When the input is not a date.
     */
    public Deadline(String s, String by) throws LilyException {
        super(s);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException dtpe) {
            throw new LilyException(LilyException.FORMAT_DATE);
        }
    }

    /**
     * Returns the Deadline as a String 
     * 
     * @return Deadline in the form of "[D][ ] Description (by: due)".
     */
    @Override
    public String toString() {
        String date = by.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
} 