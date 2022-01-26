import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class extends the Task class by adding an event time.
 *
 * @author Rdac0
 */
public class Event extends Task{
    private LocalDate time;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Creates an Event object.
     *
     * @param name The name of the Event.
     * @param time The time of the Event.
     */
    public Event(String name, String time) throws DateTimeParseException {
        super(name);
        this.time = LocalDate.parse(time);
    }

    public String toString() {
        String mark;
        if (super.getDone()) {
            mark = "[X] ";
        } else {
            mark = "[ ] ";
        }
        return "[E]" + mark + getName() +
                " (at: " + this.time.format(formatter) + ")";
    }
}
