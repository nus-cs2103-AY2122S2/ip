package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event that occurs on a specific date.
 */
public class Event extends ScheduledTask {

    /**
     * Initialises an Event task with a specified
     * description and start time.
     *
     * @param description the description of this Event task
     * @param date the date when this Event task starts
     */
    public Event(String description, LocalDate date) {
        super(description, date);
    }

    /**
     * Returns the string representation of this Event task.
     *
     * @return the string representation of this Event task.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]")
                .append(super.toString())
                .append(" (at: ")
                .append(getDate().format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                .append(")");
        return sb.toString();
    }
}
