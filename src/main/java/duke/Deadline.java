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
     * Initialises deadline task with a description (task name)
     * as well as an end datetime
     *
     * @param description Name of deadline task
     * @param endTime End datetime of deadline task in d/MM/yyyy HHmm format, e.g. 9/11/2001 1500
     */
    public Deadline(String description, LocalDateTime endTime) {
        super(description, Type.DEADLINE);

        assert description != "" && endTime != null;
        this.endTime = endTime;
    }

    /**
     * Returns end datetime in LocalDateTime datatype
     *
     * @return End datetime
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns end datetime as a string in the format given
     * in the formatString parameter
     *
     * @param formatString Format of datetime string returned
     * @return End datetime as a string
     */
    public String getEndTimeString(String formatString) {
        return getEndTime().format(DateTimeFormatter.ofPattern(formatString));
    }

    /**
     * Returns end datetime as a string in the format dd/MM/yyyy HHmm
     *
     * @return End datetime as a string using the default format
     */
    public String getEndTimeString() {
        return getEndTimeString(Ui.DATE_TIME_FORMAT);
    }

    /**
     * Returns string representation of the deadline task
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
