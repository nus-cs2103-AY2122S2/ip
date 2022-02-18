package duke.task;

import java.util.Objects;
import java.util.Optional;

/**
 * Public class for event tasks.
 */
public class Event extends Task {

    private String eventDuration;

    public Event(String task, String eventDuration) {
        super(task);
        this.eventDuration = eventDuration;
    }

    public Event(String status, String task, String eventDuration) {
        super(task, Objects.equals(status, "1") ? true : false);
        this.eventDuration = eventDuration;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public Optional<String> getTime() {
        return Optional.of(eventDuration);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + eventDuration + ")";
    }
}
