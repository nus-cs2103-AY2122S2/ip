package athena.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a single event in a task list.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy hh:mma");
    private final LocalDateTime eventDateTime;

    /**
     * Constructs a new event with the given name and eventDateTime.
     *
     * @param name The name of the event.
     * @param eventDateTime The date and time of the event.
     */
    public Event(String name, LocalDateTime eventDateTime) {
        super(name);
        this.eventDateTime = eventDateTime;
    }

    /**
     * Constructs a new event object with the given name and eventDateTime String.
     *
     * @param name The name of the event.
     * @param eventDateTime The eventDateTime String in MMM d yyyy hh:mma format.
     *                      (see LocalDateTime docs for notation)
     */
    public Event(String name, String eventDateTime) {
        super(name);
        this.eventDateTime = LocalDateTime.parse(eventDateTime, OUTPUT_FORMATTER);
    }

    /**
     * @inheritDocs
     */
    @Override
    public String getSaveFormat() {
        String eventDateTimeString = eventDateTime.format(OUTPUT_FORMATTER);
        return String.join(SAVE_SEPARATOR, super.getSaveFormat(), eventDateTimeString);
    }

    /**
     * @inheritDocs
     */
    @Override
    public String getIcon() {
        return "E";
    }

    /**
     * @inheritDocs
     */
    @Override
    public String toString() {
        String eventDateTimeString = eventDateTime.format(OUTPUT_FORMATTER);
        return super.toString() + String.format(" (at: %s)", eventDateTimeString);
    }
}
