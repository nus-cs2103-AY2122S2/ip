package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class for storing deadline tasks
 */
public class Deadline extends Task {

    /**
     * End datetime of deadline task
     */
    protected LocalDateTime endTime;

    /**
     * Initialise deadline task with a description (task name)
     * as well as an end datetime
     *
     * @param description Name of deadline task
     * @param end End datetime of deadline task in d/MM/yyyy HHmm format, e.g. 9/11/2001 1500
     */
    public Deadline(String description, String end) {
        super(description, Type.DEADLINE);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime endDate = LocalDateTime.parse(end, format);
        this.endTime = endDate;
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
     * Return string representation of the deadline task
     * e.g. [D][X] Sample deadline task (by: 12/12/2022 2100)
     *
     * @return String representation of deadline task:
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + "(by: "
                + getEndTimeString()
                + ")";
    }
}