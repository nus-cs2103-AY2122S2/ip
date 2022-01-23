import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tasks that has as specific start time and end times.
 */
public class Event extends Task {

    /**
     * String representation of the event start dates/times.
     */
    protected LocalDateTime start;

    /**
     * String representation of the event end dates/times.
     */
    protected LocalDateTime end;

    /**
     * Constructor to create a deadline task.
     *
     * @param description text description of the Event.
     * @param start start dates/times.
     * @param end end dates/times.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description, TaskType.EVENT);
        this.start = start;
        this.end = end;
    }

    /**
     * Outputs the start and end times of event in a
     * formatted manner.
     *
     * @return the start and end time of Event.
     */
    public String getAt() {
        if (start.toLocalDate().equals(end.toLocalDate())) {
            return start.format(DateTimeFormatter.ofPattern("h:mm a")) + " - "
                    + end.format(DateTimeFormatter.ofPattern("h:mm a")) + " "
                    + start.format(DateTimeFormatter.ofPattern("MMMM d yyyy"));
        } else {
            return start.format(DateTimeFormatter.ofPattern("h:mm a")) + " "
                    + start.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + " - "
                    + end.format(DateTimeFormatter.ofPattern("h:mm a")) + " "
                    + end.format(DateTimeFormatter.ofPattern("MMMM d yyyy"));
        }
    }

    /**
     * Outputs the string of start date/time using LocalDateTime such that
     * can be parsed by Java.
     *
     * @return deadline as string convenient to be parsed.
     */
    public String startToString() {
        return start.toString();
    }

    /**
     * Outputs the string of end date/time using LocalDateTime such that
     * can be parsed by Java.
     *
     * @return deadline as string convenient to be parsed.
     */
    public String endToString() {
        return end.toString();
    }

    /**
     * Outputs the string to represent Event with
     * description and at dates/times.
     *
     * @return formatted string representing the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getAt() + ")";
    }
}