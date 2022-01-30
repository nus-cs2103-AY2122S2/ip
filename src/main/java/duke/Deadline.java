package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a {@link Task} that has a deadline to meet.
 */
public class Deadline extends Task {
    private final LocalDate date;

    public Deadline(String content, LocalDate date) {
        super(content);
        this.date = date;
    }

    /**
     * returns a string representation of the deadline to be seen by users.
     *
     * @return a string representation of the deadline to be seen by users.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.BASIC_ISO_DATE) + ")";
    }

    /**
     * returns a string representation of the deadline to be used for data storage.
     *
     * @return a string representation of the deadline to be used for data storage.
     */
    @Override
    public String toData() {
        String isFinishedData;
        if (super.finished) {
            isFinishedData = "1";
        } else {
            isFinishedData = "0";
        }
        return "D:" + isFinishedData + ":" + super.content + ":" + date.format(DateTimeFormatter.ISO_DATE);
    }
}
