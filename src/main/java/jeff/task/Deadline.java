package jeff.task;

import jeff.main.JeffException;

import jeff.parser.DateParse;
import jeff.parser.TimeParse;

/**
 * Deadline class is a task customised to store end date of deadlines.
 */
public class Deadline extends Task {

    protected String dateInfo;
    protected String originalDate;
    protected DateParse date;
    protected TimeParse time;

    /**
     * Constructor for Deadline class.
     *
     * @param description Name of the Deadline.
     * @param dateInfo Information for which this task is due.
     * @throws JeffException When no available format is available to parse dateInfo.
     */
    public Deadline(String description, String dateInfo) throws JeffException {
        super(description);
        this.originalDate = dateInfo;
        String[] str = dateInfo.split(" ", 2);
        this.date = new DateParse(str[0]);
        this.time = new TimeParse(str[1]);
        this.dateInfo = this.date.toString() + " " + this.time.toString();
    }

    /**
     * Returns the identity of this Task class.
     *
     * @return D for Deadline.
     */
    public String whatType() {
        return "D";
    }

    /**
     * Returns the original string of dateInfo.
     * Used for saving to file.
     *
     * @return Original string of dateInfo.
     */
    public String getOriginalDate() {
        return this.originalDate;
    }

    /**
     * toString method specific for Deadline class,
     * inherits toString() fromTask class while adding additional information.
     * Like the type of task, [D], and date information.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateInfo + ")";
    }
}
