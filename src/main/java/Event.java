import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent the event of task.
 */
public class Event extends Task {
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Constructor for Event.
     *
     * @param description description of the task.
     * @param at by when the event at.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.date = super.getTaskDate(at);
        this.time = super.getTaskTime(at);
    }

    /**
     * String representation of event.
     *
     * @return [E] with the status and description of the task,
     *         and at when.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                +  " " + time + ")";
    }
}

