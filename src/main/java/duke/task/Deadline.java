package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that needs to be done before a specific date.
 */
public class Deadline extends Task {

    LocalDate date;


    /**
     * Constructor for deadline task.
     * @param activity the activity that needs to be done.
     * @param by the date of the deadline, yyyy-mm-dd format.
     */
    public Deadline(String activity, String by) {
        super(activity, "D");
        date = LocalDate.parse(by.trim());
    }

    /** {@inheritDoc} */
    @Override
    public String printTask() {
        if (this.status) {
            return "[" + type + "][X] " + activity + " (by " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[" + type + "][ ] " + activity + " (by " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return type + "|" + status + "|" + activity + "|" + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "|\n";
    }

}
