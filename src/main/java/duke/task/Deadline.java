package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that needs to be done before a specific date.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private LocalDate date;


    /**
     * Constructor for deadline task.
     * @param activity the activity that needs to be done.
     * @param by the date of the deadline, yyyy-mm-dd format.
     */
    public Deadline(String activity, String by) {
        super(activity, "D");
        assert  by != null;
        date = LocalDate.parse(by.trim());
    }

    /** {@inheritDoc} */
    @Override
    public String printTask() {
        if (this.isMarked) {
            return "[" + type + "][X] " + activity + " (by " + date.format(PRINT_FORMAT) + ")";
        } else {
            return "[" + type + "][ ] " + activity + " (by " + date.format(PRINT_FORMAT) + ")";
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return type + "|" + isMarked + "|" + activity + "|" + date.format(PRINT_FORMAT) + "|\n";
    }

}
