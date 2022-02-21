package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a {@link Task} that will be done in a fixed time slot.
 */
public class Event extends Task {
    private final LocalDate time;

    /**
     * Creates an Event
     *
     * @param content description of the event
     * @param time    start date of the event
     */
    public Event(String content, LocalDate time) {
        super(content);
        this.time = time;
    }

    public static String contentTimeDivder() {
        return "/at ";
    }

    /**
     * returns a string representation of the event to be seen by users.
     *
     * @return a string representation of the event to be seen by users.
     */
    @Override
    public String toString() {
        assert this.time != null;
        return "[E]" + super.toString() + " (at: " + time.format(DateTimeFormatter.BASIC_ISO_DATE) + ")";
    }

    /**
     * returns a sting representation of the event to be used for data storage.
     *
     * @return a sting representation of the event to be used for data storage.
     */
    @Override
    public String toData() {
        assert this.time != null;
        String isFinishedData;
        if (super.finished) {
            isFinishedData = "1";
        } else {
            isFinishedData = "0";
        }
        return "E:" + isFinishedData + ":" + super.content + ":" + time.format(DateTimeFormatter.ISO_DATE);
    }
}
