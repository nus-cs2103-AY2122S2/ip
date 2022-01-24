import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {

    /** Stores the time span of the event. */
    private LocalDate date;
    private LocalTime time1;
    private LocalTime time2;

    /**
     * Constructor of Event class.
     * @param description The description of the event.
     * @param duration The time span of the event.
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
        return "[E]" + super.toString() + " (at: " + this.date + " " + time1 + " " + time2 + " " + ")";
    }
}
