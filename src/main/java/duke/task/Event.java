package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 * Event represents a Task that a user must attend at a certain date and time.
 *
 * @author Jian Rong
 */
public class Event extends Task {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructor of Event class.
     * @param title Title of Event
     * @param date Date of Event
     * @param time Time of Event
     */
    public Event(String title, LocalDate date, LocalTime time) {
        super(title);
        this.date = date;
        this.time = time;
    }

    /**
     * Returns a summary of the Event Task.
     * @return The summary of the Event.
     */
    public String toString() {
        int day = date.getDayOfMonth();
        Month month = date.getMonth();
        int year = date.getYear();
        String result = "";
        switch (this.priority) {
            case LOW:
                result = "[L]";
                break;
            case MEDIUM:
                result = "[M]";
                break;
            case HIGH:
                result = "[H]";
                break;
        }
        if (this.isChecked) {
            result += "[D][X]";
        } else {
            result += "[D][ ]";
        }
        return (result + String.format(" %s (at: %d %s %d %s)", title, day, month, year, time));
    }

}
