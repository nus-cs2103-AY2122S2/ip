package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represents an event to be done. This event is a task that possesses a state/status that is by default
 * not done
 */
public class Event extends Task {

    protected LocalDate date;
    protected String time;
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates an event with a time, that is initially not done
     *
     * @param description A short description of the event
     * @param date        Date and time (if applicable) of the event as a String
     */
    public Event(String description, String date) throws DukeException {
        super(description);
        this.time = "";
        String[] dateSplit = date.split(" ", 2);
        try {
            this.date = LocalDate.parse(dateSplit[0]);
            if (dateSplit.length != 1) {
                this.time = dateSplit[1];
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date! Date format to be of type yyyy-mm-dd\n");
        }
    }

    /**
     * Returns true if time of event was given as input
     *
     * @return true if event has a time
     */
    private boolean hasTime() {
        return !this.time.equalsIgnoreCase("");
    }

    /**
     * Returns the String representation of an Event
     *
     * @return String format of a Deadline
     */
    @Override
    public String toString() {
        StringBuilder eventTime = new StringBuilder();
        if (hasTime()) {
            eventTime.append(" ").append(time);
        }
        return "[E]" + super.toString() + " (at: " + date.format(pattern) + eventTime + ")";
    }

    /**
     * Returns the String format of how the Event will be saved in the text file
     *
     * @return String format of the Event task to be saved
     */
    public String toSave() {
        return String.format("E | %d | %s | %s | %s", this.isDone ? 1 : 0, this.description, this.date, this.time);
    }
}
