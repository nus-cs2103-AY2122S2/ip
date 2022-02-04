package heylo.tasks;

import java.time.LocalDate;

import heylo.util.DateFormatter;

/**
 * Represents an event task with a start time.
 */
public class Event extends Task {
    protected LocalDate duration;

    /**
     * Creates an event task with duration.
     *
     * @param description String event description.
     * @param duration    String event start time.
     */
    public Event(String description, String duration) {
        super(description);
        try {
            this.duration = LocalDate.parse(duration);
        } catch (Exception e) {
            this.duration = null;
        }
    }

    /**
     * Converts the event task and its data to string format.
     *
     * @return String event.
     */
    @Override
    public String toString() {
        return " [E]" + super.toString() + "\t (at " + DateFormatter.formatDateInLongForm(duration) + ")";
    }
}
