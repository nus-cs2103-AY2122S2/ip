package karen.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Stores the (/at) date in which the Event occurs at
 */
public class Event extends Task {
    protected LocalDate atDate;

    public Event(String description, String at) {
        super(description);
        atDate = this.parseDate(at);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toSaveData() {
        return String.format("E|%s|%s|%s", isDone, this.getDescription(), atDate);
    }

    /**
     *  {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
