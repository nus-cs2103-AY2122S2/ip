package duke.task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

import duke.DukeException;

/**
 * Represents a task with a time and duration.
 */
public class Event extends Task {
    private final LocalDateTime at;
    private final Duration duration;

    /**
     * Constructs an Event instance.
     *
     * @param description The description of the Event.
     * @param at          The date and time of the Event.
     * @param duration    The duration of the Event.
     */
    public Event(String description, LocalDateTime at, Duration duration) {
        super(description);

        if (at == null) {
            throw new DukeException("The time of an Event must be specified");
        }

        if (duration == null) {
            throw new DukeException("The duration of an Event must be specified");
        }

        this.at = at;
        this.duration = duration;
    }

    /**
     * Constructs an Event instance.
     *
     * @param description The description of the Event.
     * @param at          The date and time of the Event.
     * @param duration    The duration of the Event.
     * @param isDone      Whether the Event is done or not.
     */
    public Event(String description, LocalDateTime at, Duration duration, boolean isDone) {
        this(description, at, duration);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mm a");
        return "[E]" + super.toString() + " (at: " + at.format(formatter) + " - " + at.plus(duration).format(formatter)
                + ")";
    }

    @Override
    public String toSaveData() {
        StringJoiner joiner = new StringJoiner(" | ");
        joiner.add("E").add(String.valueOf(isDone ? 1 : 0)).add(description).add(at.toString())
                .add(duration.toString());
        return joiner.toString();
    }
}
