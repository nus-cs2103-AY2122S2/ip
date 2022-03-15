package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event, a type of Task that is set at a given time
 */
public class Event extends Task {
    protected LocalDateTime setAt;

    /**
     * Constructor of the Event class
     * @param description string representing the details of the Event
     * @param setAt LocalDateTime object representing the time the task is set at
     */
    public Event(String description, LocalDateTime setAt) {
        super(description);
        this.setAt = setAt;
    }

    @Override
    public String toString() {
        return String.format("[E]%s(at: %s)", super.toString(),
                                this.setAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toStorageString() {
        return String.format("E#%s#%s#%s", this.getStatusIcon(), this.description,
                                this.setAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
