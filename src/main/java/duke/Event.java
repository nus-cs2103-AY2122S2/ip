package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /** Stores the time span of the event. */
    private LocalDate date;
    private LocalTime time1;
    private LocalTime time2;

    /**
     * Constructor of Event class.
     * @param description The description of the event.
     * @param date The time span of the event.
     */

    public Event(String description, LocalDate date, boolean isDone ) {
        super(description);
        this.date=  date;
        if (isDone) {
            this.markAsDone();
        }
    }
    public Event(String description, LocalDate date, LocalTime t1, LocalTime t2, boolean isDone ) {
        super(description);
        this.date=  date;
        this.time1 = t1;
        this.time2 = t2;
        if (isDone) {
            this.markAsDone();
        }
    }
        @Override
        public String writeToFile() {
            return " E " + super.writeToFile() + " | " + this.date + " | " + this.time1 + " | " + this.time2;
        }

    /**
     * Returns the task in proper format.
     * @return String of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " from " + time1.format(DateTimeFormatter.ofPattern("hh: mm a")) + " to " + time2.format(DateTimeFormatter.ofPattern("hh: mm a")) + " " + ")";
    }
}
