package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which is an event.
 */
public class Event extends Task {

    /** The date of the event */
    private LocalDate eventDate;
    /** The starting time of the event */
    private LocalTime eventStartTime;
    /** The ending time of the event */
    private LocalTime eventEndTime;


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
        this.eventDate =  date;
        this.eventStartTime = t1;
        this.eventEndTime = t2;
        if (isDone) {
            this.markAsDone();
        }
    }

    /**
     * Returns a standardized format for storing the event into the data file.
     *
     * @return String of event in data file storage format.
     */
    @Override
    public String writeToFile() {
        return " E " + super.writeToFile() + " | " + this.eventDate + " | " + this.eventStartTime + " | "
                + this.eventEndTime + "\n";
    }

    /**
     * Adds extra custom formatting for user view specific to events.
     *
     * @return A custom String display of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " from " + eventStartTime.format(DateTimeFormatter.ofPattern("hh: mm a")) + " to "
                + eventEndTime.format(DateTimeFormatter.ofPattern("hh: mm a")) + " " + ")";
    }
}
