package duke.tasks;

import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate eventBy;

    public Event(String description, LocalDate eventBy) {
        super(description);
        this.eventBy = eventBy;
    }

    public LocalDate getEventBy() {
        return eventBy;
    }

    public void setEventBy(LocalDate eventBy) {
        this.eventBy = eventBy;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + eventBy + ")";
    }
}
