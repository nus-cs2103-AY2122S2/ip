package siri;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the event task
 */
public class Event extends Task {

    protected String eventDate;
    protected LocalDate date;

    public Event(String description, String initialLetter, String eventDate) {
        super(description, initialLetter);
        this.eventDate = eventDate;
    }

    //reused from Brigette Santoso E0564307
    public Event(String description, String initialLetter, LocalDate date) {
        super(description, initialLetter);
        this.eventDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.date = date;

    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] "
                + this.description
                + " (at: " + this.eventDate + ")";
    }

}
