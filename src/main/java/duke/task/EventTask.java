package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a local date time.
 */

public class EventTask extends Task {
    /**
     * Preposition describing the date (eg. on, by).
     */
    private String preposition;
    /**
     * Date and time of the event.
     */
    private LocalDateTime dateTime;

    /**
     * Initializes a new event.
     *
     * @param name        Name of the event.
     * @param preposition Preposition describing the date.
     * @param dateTime    Date and time of the event.
     */
    public EventTask(String name, String preposition, LocalDateTime dateTime) {
        super(name);
        this.preposition = preposition;
        this.dateTime = dateTime;
    }

    public String getPreposition() {
        return this.preposition;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns a formatted string including the type indicating it is an event.
     *
     * @return
     */
    public String toString() {
        String prefix = "[E]";
        String stateAndName = super.toString();
        String prepositionAndDateTime = String.format(" (%s: %s)",
                this.preposition,
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));

        return prefix + stateAndName + prepositionAndDateTime;
    }
}
