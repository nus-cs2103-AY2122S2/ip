package mike;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task. An event is a task with an "at" date.
 */
public class Event extends Task {
    private final String eventDate;
    private final LocalDate date;
    private static final String taskType = "E";

    private static LocalDate convertToDate(String endDate) {
        return LocalDate.parse(endDate);
    }

    /**
     * Constructor for event.
     *
     * @param name Event name.
     * @param eventDate Event date.
     */
    public Event(String name, String eventDate) {
        super(name);
        this.eventDate = eventDate;
        this.date = convertToDate(eventDate);
    }

    /**
     * Constructor for event.
     *
     * @param name Event name.
     * @param eventDate Event date.
     * @param isDone Whether event is marked as done.
     */
    public Event(String name, String eventDate, boolean isDone) {
        super(name, isDone);
        this.eventDate = eventDate;
        this.date = convertToDate(eventDate);
    }

    /**
     * Returns a new event object with the isDone field set to true.
     *
     * @return Event object with isDone set to true.
     */
    public Event markAsDone() {
        return new Event(this.name, this.eventDate, true);
    }

    /**
     * Returns a new event object with the isDone field set to false.
     *
     * @return Event object with isDone set to false.
     */
    public Event markAsUndone() {
        return new Event(this.name, this.eventDate, false);
    }

    /**
     * Returns the event as a String in a format that can be stored.
     *
     * @return String representation of event.
     */
    public String convertToStoredTaskFormat() {
        String doneIndicator = "false";
        if (super.isDone) {
            doneIndicator = "true";
        }
        String storedListFormat = String.format("%s|%s|%s|%s", taskType,
                doneIndicator, super.name, this.date);
        return storedListFormat;
    }

    /**
     * Returns a String representing the event object.
     *
     * @return String representing the event object.
     */
    @Override
    public String toString() {
        String doneMark;
        if (super.isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }
        String dateOutput = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[%s][%s] %s (at: %s)",
                taskType, doneMark, super.name, dateOutput);
    }
}
