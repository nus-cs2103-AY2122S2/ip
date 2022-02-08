package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

/**
 * Class for event related tasks.
 */
public class Event extends Task {
    protected LocalDate eventDate;
    protected DayOfWeek day;
    protected Month month;
    protected int year;
    protected LocalTime time;
    protected Tag tag;

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
     * Constructs event object.
     *
     * @param description event description.
     * @param eventDate event date.
     * @param time event time.
     * @param tag event tag.
     */
    public Event(String description, LocalDate eventDate, LocalTime time, Tag tag) {
        super(description);
        this.eventDate = eventDate;
        this.day = eventDate.getDayOfWeek();
        this.month = eventDate.getMonth();
        this.year = eventDate.getYear();
        this.time = time;
        this.tag = tag;
    }

    /**
     * Tags event object.
     *
     * @param taskTag event tag.
     * @return event object.
     */
    @Override
    public Event tag(Tag taskTag) {
        return new Event(description, eventDate, time, taskTag);
    }

    /**
     * Returns a string representation to save to disk.
     *
     * @return String representation to save to disk.
     */
    @Override
    public String toStringForSave() {
        return tag == null
                ? "E " + super.toStringForSave() + " # " + this.eventDate + " " + this.time
                : "E " + super.toStringForSave() + " # " + this.eventDate + " " + this.time + " " + this.tag.toString();
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String represenation.
     */
    @Override
    public String toString() {
        return tag == null
                ? ("[E]" + super.toString() + " (at: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + ")"
                + "\n")
                : ("[E]" + super.toString() + " (at: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + " "
                + this.tag.toString() + ")"
                + "\n");
    }
}
