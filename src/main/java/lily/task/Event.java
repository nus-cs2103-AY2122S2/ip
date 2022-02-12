package lily.task;

import lily.LilyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Task which records the time of the event as a LocalDate.
 * 
 * @author Hong Yi En, Ian
 * @version Feb 2022 (AY21/22 Sem 2)
 */
public class Event extends Task {
    private LocalDate at;

    /**
     * Creates an Event object.
     * 
     * @param s The description of what is happening.
     * @param at When the event is happening.
     * @throws LilyException When the input is not a date.
     */
    public Event(String s, String at) throws LilyException {
        super(s);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException dtpe) {
            throw new LilyException(LilyException.FORMAT_DATE);
        }
    }

    /**
     * Returns the Event as a String.
     * 
     * @return Deadline in the form of "[E][ ] Description (at: due)".
     */
    @Override
    public String toString() {
        String date = at.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        return "[E]" + super.toString() + " (at: " + date + ")";
    }
} 