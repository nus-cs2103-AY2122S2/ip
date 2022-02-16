package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a local date time.
 */

public class DeadlineTask extends Task {
    /**
     * Preposition describing the date (eg. on, by).
     */
    private String preposition;
    /**
     * Date and time of the deadline.
     */
    private LocalDateTime dateTime;

    /**
     * Initializes a new ∂eadline task.
     *
     * @param name        Name of the event.
     * @param preposition Preposition describing the date.
     * @param dateTime    Date and time of the event.
     */
    public DeadlineTask(String name, String preposition, LocalDateTime dateTime) {
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
     * Returns a formatted string including the type indicating it is a deadline task.
     *
     * @return
     */
    public String toString() {
        String prefix = "[D]";
        String stateAndName = super.toString();
        String prepositionAndDateTime = String.format(" (%s: %s)",
                this.preposition,
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));

        return prefix + stateAndName + prepositionAndDateTime;
    }
}
