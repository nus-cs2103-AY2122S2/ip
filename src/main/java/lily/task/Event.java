package lily.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Task which records the time of the event as a LocalDate.
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Create an Event object.
     * 
     * @param s The description of what is happening.
     * @param at When the event is happening.
     * @throws DateTimeParseException When the input is not a date.
     */
    public Event(String s, String at) throws DateTimeParseException {
        super(s);
        this.at = LocalDate.parse(at);
    }

    /**
     * Returns the Event as a String.
     * 
     * @return Deadline in the form of "[E][ ] Description (at: due)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " 
        + at.format(DateTimeFormatter.ofPattern("dd MMM yy")) + ")";
    }
} 