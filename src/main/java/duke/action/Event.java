package duke.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An extension from Action. The Event class
 * has a variable date of type LocalDateTime
 * that is specific to itself.
 */
public class Event extends Action {

    private LocalDateTime date;

    /**
     * Constructs a new Event object with the variables task and at.
     * @param task event
     * @param at date and time values of the event
     */
    public Event(String task, String at) {
        super(task);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m");
        this.date = LocalDateTime.parse(at, format);
    }

    /**
     * Constructs a new Event object with the variables task, date
     * and bool.
     * @param task event
     * @param date date and time values of the event
     * @param bool done status of the event
     */
    public Event(String task, LocalDateTime date, boolean bool){
        super(task, bool);
        this.date = date;
    }

    /**
     * Returns a new Event object with same variable values except
     * isDone which is now true
     * @return marked Event object
     */
    @Override
    public Action setDone()  {
        return new Event(act, date, true);
    }

    /**
     * Returns a new Event object with same variable values except
     * isDone which is now false
     * @return unmarked Event object
     */
    @Override
    public Action setUnDone()  {
        return new Event(act, date, false);
    }

    /**
     * Returns a string representation of the Event object.
     * @return string representation
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy, H:m")) + ")";
    }
}