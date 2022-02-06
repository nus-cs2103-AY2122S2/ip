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
        System.out.println("added: " + this.toString());
    }

    /**
     * Returns a summary of the Event Task.
     * @return The summary of the Event.
     */
    public String toString() {
        int day = date.getDayOfMonth();
        Month month = date.getMonth();
        int year = date.getYear();
        if (this.isChecked) {
            return String.format("[D][X] %s (at: %d %s %d %s)", title, day, month, year, time);
        } else {
            return String.format("[D][ ] %s (at: %d %s %d %s)", title, day, month, year, time);
        }
    }

}
