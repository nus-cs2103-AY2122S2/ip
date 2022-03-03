package duke.task;

/**
 * Represents a task that occurs at a particular date and/or time.
 * Note: the allowable formats for date and time to be interpreted
 * correctly by Deadline is YYYY/MM/DD (with ./| being valid separators)
 * and HHMM.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.1
 */
public class Deadline extends TaskWithDateTime {

    /**
     * Constructor for Deadline specifying description, dateTime string.
     *
     * @param description description of Deadline
     * @param by dateTime string associated with Deadline
     */
    public Deadline(String description, String by) {
        super(description, by);
    }

    /**
     * Default toString method that returns formatted Deadline.
     *
     * @return formatted string of the description, dateTime and completeness
     * status of Deadline with a Deadline marker.
     */
    @Override
    public String toString() {
        String s = "[D]" + super.toString();
        return s.replace(" (at: ", " (by: ");
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
        return "D | " + super.writeToFile();
    }
}
