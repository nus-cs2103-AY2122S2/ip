package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which is an event.
 */
public class Event extends Task {

    /** The date of the event */
    private LocalDate date;

    /** The starting time of the event */
    private LocalTime time1;

    /** The ending time of the event */
    private LocalTime time2;

    /**
     * Constructor of Event class.
     *
     * @param description The description of the event.
     * @param date The date of the event as a LocalDate object.
     * @param isDone The completion status of the event.
     */
    public Event(String description, LocalDate date, boolean isDone ) {
        super(description);
        this.date=  date;
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Constructor of Event class.
     *
     * @param description The description of the event.
     * @param date The date of the event as a LocalDate object.
     * @param t1 The start time of the event as a LocalTime object.
     * @param t2 The end time of the event as a LocalTime object.
     * @param isDone The completion status of the event.
     */
    public Event(String description, LocalDate date, LocalTime t1, LocalTime t2, boolean isDone ) {
        super(description);
        this.date=  date;
        this.time1 = t1;
        this.time2 = t2;
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Returns a standardized format for storing the deadline into the data file.
     *
     * @return String of deadline in data file storage format.
     */
    @Override
    public String writeToFile() {
        return " E " + super.writeToFile() + " | " + this.date + " | " + this.time1 + " | " + this.time2;
    }

    /**
     * Adds extra custom formatting for user view specific to events.
     *
     * @return A custom String display of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " from " + time1.format(DateTimeFormatter.ofPattern("hh: mm a")) + " to " + time2.format(DateTimeFormatter.ofPattern("hh: mm a")) + " " + ")";
    }
}
