package yale.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Subclass of Task.
 * Added modification of genericTask object.
 */
public class Event extends Task {
    /**
     * String to represent the date
     * and time of event.
     */
    protected LocalDate at;

    /**
     * Constructor method.
     * @param name Name of event.
     * @param isMarked Boolean of whether event is marked.
     * @param at Date of event.
     */
    public Event(String name, boolean isMarked, String at) {
        super(name, isMarked);
        this.at = LocalDate.parse(at);
    }

    /**
     * Returns a customised String format of
     * the Event object.
     * @return Custom String format of Event object.
     */
    @Override
    public String export() {
        return "E " + "| " + (isMarked? 1 : 0) + " | " + this.name + " | " + this.at;
    }
    /**
     * Returns a customised String.
     * @return Customised String format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
