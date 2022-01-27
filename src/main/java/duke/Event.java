package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Represents a <code>Task</code> that occurs at a given time
 */
public class Event extends Task {
    private String eventTime;
    private LocalDate date;

    Event(String description, String eventTime, Boolean completed) {
        super(description, completed);
        this.eventTime = eventTime;
        try {
            new SimpleDateFormat("yyyy-mm-dd").parse(eventTime);
            this.date = DateParser.parseDate(eventTime);
        } catch (ParseException e) {
            this.date = null;
        }
    }

    Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
        try {
            new SimpleDateFormat("yyyy-mm-dd").parse(eventTime);
            this.date = DateParser.parseDate(eventTime);
        } catch (ParseException e) {
            this.date = null;
        }
    }

    /**
     * Obtains the type of <code>Task</code>
     * @return String corresponding to the type of <code>Task</code>
     */
    @Override
    public String getType() {
        return "Event";
    }

    /**
     * Obtains the time of the <code>Event</code> object
     * @return String corresponding to the time of the <code>Task</code>
     */
    public String getTime() {
        return this.eventTime;
    }

    /**
     * Obtains the string representation of the <code>Event</code> object
     * @return String corresponding to the <code>Event</code> object
     */
    @Override
    public String toString() {
        String printedDate;
        if (date != null) {
            printedDate = DateParser.dateToString(this.date);
        } else {
            printedDate = this.eventTime;
        }
        return "[E]" + super.toString() + " (at: " + printedDate + ")";
    }
}
