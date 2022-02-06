package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event type of task.
 */
public class Event extends Task {
    protected LocalDate eventTime;

    /**
     * Constructor for Event.
     * @param description the description for the task.
     * @param isDone status of the task.
     * @param eventTime event time of the task.
     */
    public Event(String description, boolean isDone, LocalDate eventTime) {
        super(description, isDone);
        this.eventTime = eventTime;
    }

    /**
     * Another constructor for Event.
     * @param description the description for the task.
     * @param eventTime status of the task.
     */
    public Event(String description, LocalDate eventTime) {
        this(description, false, eventTime);
    }

    /**
     * Get the saved message corresponding to this task.
     * @return save message for the task.
     */
    @Override
    public String getSaveMessage() {
        if (this.isDone) {
            return String.format("E / 1 / %s / %s", this.description, this.eventTime);
        } else {
            return String.format("E / 0 / %s / %s", this.description, this.eventTime);
        }
    }

    @Override
    public String toString() {
        String timeString = this.eventTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        if (this.isDone) {
            return String.format("[E][X] %s (at: %s)", this.description, timeString);
        } else {
            return String.format("[E][ ] %s (at: %s)", this.description, timeString);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }

        Event event = (Event) o;

        return event.description.equals(this.description);
    }
}