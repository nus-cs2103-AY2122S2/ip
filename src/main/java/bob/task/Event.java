package bob.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * {@inheritDoc}
 */
public class Event extends Task {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    /**
     * Constructor for an event.
     * @param name the event's name
     * @param date the event's date
     * @param startTime starting time of the event
     * @param endTime ending time of the even
     */
    public Event(String name, LocalDate date, LocalTime startTime, LocalTime endTime) {
        super(name);
        super.unmarkTask();
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        super.setType("E");
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.of(date, startTime);
    }

    @Override
    public String printStatus() {
        return "[E] " + super.getStatusSymbol() + " " + this + "\n\t(at: "
                + this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " from " + this.startTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " to "
                + this.endTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }

    @Override
    public String toString() {
        return super.getName();
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Todo) {
            return -1;
        } else if (o instanceof Deadline) {
            Deadline dl = (Deadline) o;
            return this.getDateTime().compareTo(dl.getDateTime());
        } else {
            Event event = (Event) o;
            return this.getDateTime().compareTo(event.getDateTime());
        }
    }
}
