package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event type of Task. Has specific time of occurence.
 */
public class Event extends Task {
    private String eventTime;
    private LocalDate date;

    /**
     * Constructor of Event.
     *
     * @param name
     * @param eventTime
     */
    public Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;

        //try parsing the eventTime as a date to set date
        try {
            this.date = LocalDate.parse(eventTime);
        } catch (DateTimeParseException e) {
            //dont set date if not in right format
        }
    }

    /**
     * Aleternative Construcotr of Event to set completion status.
     *
     * @param name
     * @param eventTime
     * @param isCompleted
     */
    public Event(String name, String eventTime, boolean isCompleted) {
        super(name, isCompleted);
        this.eventTime = eventTime;
    }

    /**
     * Returns the string representation of this event for storage.
     *
     * @return
     */
    @Override
    public String toFileString() {
        return "E : " + (isCompleted ? "1 : " : "0 : ") + name + " : " + eventTime;
    }

    /**
     * Overriden toString method for Event.
     *
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        String str = "[E]";
        if (this.isCompleted) {
            str += "[X] ";
        } else {
            str += "[ ] ";
        }
        str += name + " ";
        str += "(at: " + (date != null ? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : eventTime) + ")";
        return str;
    }
}
