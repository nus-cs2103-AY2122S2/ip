import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 * This is a child class of Task, Deadline.
 * Deadline accepts another variable, 'by' that
 * stores the time this Deadline has to be completed
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates Deadline object with provided LocalDate variable
     */
    public Deadline(String n, int d, LocalDate b) {
        super(n, d);
        super.type = 'D';
        by = b;
    }

    /**
     * Creates Deadline object without provided LocalDate variable
     * Used when there is the need to parse text into date first
     */
    public Deadline(String n, int d) {
        super(n, d);
        super.type = 'D';
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    /**
     * Setting Deadline object's date it should end by
     */
    public void setStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        setBy(localDate);
    }

    /**
     * Converting LocalDate variable to String
     */
    public String convertLocalDateToString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MMMM/dd");
        return by.format(formatter);
    }

    /**
     * Producing a user-friendly view of a Deadline task
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(getTaskIcon()).append(" - ");
        res.append(this.getDoneIcon()).append(" - ");
        res.append(this.name).append(" - ");
        res.append(convertLocalDateToString()).append("\n");
        return res.toString();
    }

}
