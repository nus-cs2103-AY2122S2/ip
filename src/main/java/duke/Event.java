package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class for storing event tasks
 */
public class Event extends Task {

    /**
     * Start datetime of event task
     */
    protected LocalDateTime startTime;

    /**
     * End datetime of event task
     */
    protected LocalDateTime endTime;

    /**
     * Initialise event task with a description (task name)
     * as well as a start & end datetime
     *
     * @param description Name of event task
     * @param start Start datetime of event task in d/MM/yyyy HHmm format, e.g. 9/11/2001 1500
     * @param end End datetime of event task in d/MM/yyyy HHmm format, e.g. 9/11/2001 1500
     */
    public Event(String description, String start, String end) {
        super(description, Type.EVENT);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime startDate = LocalDateTime.parse(start, format);
        LocalDateTime endDate = LocalDateTime.parse(end, format);

        this.startTime = startDate;
        this.endTime = endDate;
    }

    /**
     * Return start datetime in LocalDateTime datatype
     *
     * @return Start datetime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Return end datetime in LocalDateTime datatype
     *
     * @return End datetime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Return start datetime as a string in the format given
     * in the formatString parameter
     *
     * @param formatString Format of datetime string returned
     * @return Start datetime as a string
     */
    public String getStartTimeString(String formatString) {
        return getStartTime().format(DateTimeFormatter.ofPattern(formatString));
    }

    /**
     * Return start datetime as a string in the format dd/MM/yyyy HHmm
     *
     * @return Start datetime as a string using the default format
     */
    public String getStartTimeString() {
        return getStartTimeString("dd/MM/yyyy HHmm");
    }

    /**
     * Return end datetime as a string in the format given
     * in the formatString parameter
     *
     * @param formatString Format of datetime string returned
     * @return End datetime as a string
     */
    public String getEndTimeString(String formatString) {
        return getEndTime().format(DateTimeFormatter.ofPattern(formatString));
    }

    /**
     * Return end datetime as a string in the format dd/MM/yyyy HHmm
     *
     * @return End datetime as a string using the default format
     */
    public String getEndTimeString() {
        return getEndTimeString("dd/MM/yyyy HHmm");
    }

    /**
     * Return string representation of the event task
     * e.g. [E][X] Sample event task (at: 12/12/2022 2100 till 12/12/2022 2300)
     *
     * @return String representation of event task:
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(at: "
                + getStartTimeString()
                +" till "
                + getEndTimeString()
                + ")";
    }
}