package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * This is a child class of a type of Task, Deadline.
 * Deadline accepts another variable, 'by' that
 * stores the time that this Deadline has to be completed
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates Deadline object with provided LocalDate variable
     * @param name of the Task (description)
     * @param done whether the Task is completed or not
     * @param by the date when this Task should be completed
     */
    public Deadline(String name, int done, LocalDate by) {
        super(name, done);
        super.type = 'D';
        this.by = by;
    }

    /**
     * Creates Deadline object without provided LocalDate variable
     * Used when there is the need to parse text into date first
     * @param name of the Task (description)
     * @param done whether the Task is completed or not
     */
    public Deadline(String name, int done) {
        super(name, done);
        super.type = 'D';
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    /**
     * Setting Deadline object's date it should end by
     * @param date provided in the format of "yyyy/MM/dd" by user
     */
    public void setLocalDate(String date) {
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
     * Producing a user-friendly view of a Deadline Task's information
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getTaskIcon()).append(" - ");
        result.append(done).append(" - ");
        result.append(name).append(" - ");
        result.append(convertLocalDateToString()).append("\n");
        return result.toString();
    }

}
