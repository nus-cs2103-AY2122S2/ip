package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /** Stores the time span of the event. */
    private LocalDate eventDate;
    private LocalTime eventStartTime;
    private LocalTime eventEndTime;

    /**
     * Constructor of Event class.
     * @param description The description of the event.
     * @param date The time span of the event.
     */
    public Event(String description, LocalDate date, boolean isDone ) {
        super(description);
        this.eventDate = date;
        if (isDone) {
            this.markAsDone();
        }
    }
    public Event(String description, LocalDate date, LocalTime t1, LocalTime t2, boolean isDone ) {
        super(description);
        this.eventDate =  date;
        this.eventStartTime = t1;
        this.eventEndTime = t2;
        if (isDone) {
            this.markAsDone();
        }
    }

    @Override
    public String writeToFile() {
        return " E " + super.writeToFile() + " | " + this.eventDate + " | " + this.eventStartTime + " | "
                + this.eventEndTime;
    }

    /**
     * Returns the task in proper format.
     * @return String of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " from " + eventStartTime.format(DateTimeFormatter.ofPattern("hh: mm a")) + " to "
                + eventEndTime.format(DateTimeFormatter.ofPattern("hh: mm a")) + " " + ")";
    }
}
