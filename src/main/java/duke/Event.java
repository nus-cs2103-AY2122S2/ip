package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class Event extends Task {

    protected LocalDate eventDate;
    protected DayOfWeek day;
    protected Month month;
    protected int year;
    protected LocalTime time;

    /**
     * Constructs an Event object.
     *
     * @param description description of event.
     * @param eventDate event date.
     * @param time event time.
     */
    public Event(String description, LocalDate eventDate, LocalTime time) {
        super(description);
        this.eventDate = eventDate;
        this.day = eventDate.getDayOfWeek();
        this.month = eventDate.getMonth();
        this.year = eventDate.getYear();
        this.time = time;
    }

    /**
     * Returns a string representation to save to disk.
     *
     * @return String representation to save to disk.
     */
    @Override
    public String toStringForSave() {
        return "E " + super.toStringForSave() + " # " + this.eventDate + " " + this.time;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String represenation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + ")";
    }
}
