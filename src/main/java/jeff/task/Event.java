package jeff.task;

import jeff.main.JeffException;
import jeff.parser.DateParse;
import jeff.parser.TimeParse;

/**
 * Event class is a task customised to store date of the Event.
 */
public class Event extends Task {

    protected String dateInfo;
    protected String originalDate;
    protected DateParse date;
    protected TimeParse time;

    /**
     * Constructor for Event class.
     *
     * @param description Name of the Event.
     * @param dateInfo Information for which this task is due.
     * @throws JeffException When no available format is available to parse dateInfo.
     */
    public Event(String description, String dateInfo) throws JeffException {
        super(description);
        this.originalDate = dateInfo;
        String[] str = dateInfo.split(" ", 2);
        String inputDate = str[0];
        String inputTime = str[1];
        assert inputDate.length() >= 8 : "Date input seems shorter than expected, expect 8 or more";
        assert inputTime.length() >= 4 : "Time input seems shorter than expected, expect 4 or more";
        this.date = new DateParse(str[0]);
        this.time = new TimeParse(str[1]);
        this.dateInfo = this.date.toString() + " " + this.time.toString();
    }

    /**
     * Returns the identity of this Task class.
     *
     * @return E for Event.
     */
    public String whatType() {
        return "E";
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
     * toString method specific for Event class,
     * inherits toString() fromTask class while adding additional information.
     * Like the type of task, [E], and date information.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateInfo + ")";
    }
}
