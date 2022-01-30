package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is a child class of duke.Task, duke.Deadline.
 * duke.Deadline accepts another variable, 'by' that
 * stores the time this duke.Deadline has to be completed
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates duke.Deadline object with provided LocalDate variable
     */
    public Deadline(String name, int isDone, LocalDate by) {
        super(name, isDone);
        super.type = 'D';
        this.by = by;
    }

    /**
     * Creates duke.Deadline object without provided LocalDate variable
     * Used when there is the need to parse text into date first
     */
    public Deadline(String name, int isDone) {
        super(name, isDone);
        super.type = 'D';
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    /**
     * Setting duke.Deadline object's date it should end by
     */
    public void setStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MMMM/dd");
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
     * Producing a user-friendly view of a duke.Deadline task
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getTaskIcon()).append(" - ");
        result.append(isDone).append(" - ");
        result.append(name).append(" - ");
        result.append(convertLocalDateToString()).append("\n");
        return result.toString();
    }

}
