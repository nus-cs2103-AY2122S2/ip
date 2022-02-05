package duke.tasks;

import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate eventBy;

    /**
     * Event constructor
     * @param description user input for event description
     * @param eventBy validated event date with type LocalDate
     */
    public Event(String description, LocalDate eventBy) {
        super(description);
        this.eventBy = eventBy;
    }

    public LocalDate getEventBy() {
        return eventBy;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventBy + ")";
    }
}
