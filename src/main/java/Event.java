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
    public Event(String description, LocalDate date ) {
        super(description);
        this.date=  date;
    }
    public Event(String description, LocalDate date, LocalTime t1, LocalTime t2 ) {
        super(description);
        this.date=  date;
        this.time1 = t1;
        this.time2 = t2;
    }

    /**
     * Returns the task in proper format.
     * @return String of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " to " + time1.format(DateTimeFormatter.ofPattern("hh: mm a")) + " " + time2.format(DateTimeFormatter.ofPattern("hh: mm a")) + " " + ")";
    }
}
