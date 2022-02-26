package duke.task;

/**
 * Represents a task that occurs at a particular date and/or time.
 * Note: the allowable formats for date and time to be interpreted
 * correctly by Deadline is YYYY/MM/DD (with ./| being valid separators)
 * and HHMM.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.0
 */
public class DoAfter extends TaskWithDateTime {

    /**
     * Constructor for DoAfter specifying description, dateTime string.
     *
     * @param description description of Deadline
     * @param after dateTime string associated with Deadline
     */
    public DoAfter(String description, String after) {
        super(description, after);
    }

    /**
     * Default toString method that returns formatted Deadline.
     *
     * @return formatted string of the description, dateTime and completeness
     * status of Deadline with a Deadline marker.
     */
    @Override
    public String toString() {
        String s = "[A]" + super.toString();
        return s.replace(" (at: ", " (after: ");
    }

    /**
     * Parses contents of Deadline into a csv-like format.
     * Delimiter is '|'.
     *
     * @return formatted string of Deadline, its dateTime, completion status
     * and a Deadline marker.
     */
    @Override
    public String writeToFile() {
        return "A | " + super.writeToFile();
    }
}