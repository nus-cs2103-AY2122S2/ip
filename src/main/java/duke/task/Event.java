package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

/**
 * A class that represents an event with date.
 */
public class Event extends Task {
    private static final String DEFAULT_DATE_FORMAT = "dd MMMM yyyy HHmm";
    private static final String SAVE_FILE_DATE_FORMAT = "d/MM/yyyy HHmm";
    private final LocalDateTime eventAt;

    /**
     * Creates an Event instance with a title and event date.
     *
     * @param title The title of the event.
     * @param eventAt The date of the event.
     */
    public Event(String title, LocalDateTime eventAt) {
        super(title);
        this.eventAt = eventAt;
    }

    /**
     * Retrieves and parses the save format for event.
     *
     * @return The save format for event
     * @throws DukeException If the class return an invalid class type,
     * it will throw a DukeException.
     */
    @Override
    public String getSaveFormat() throws DukeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(SAVE_FILE_DATE_FORMAT);
        return String.format("%s | %s", super.getSaveFormat(), this.eventAt.format(format));
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);
        return String.format("[E]%s (at: %s)", super.toString(), this.eventAt.format(format));
    }
}
