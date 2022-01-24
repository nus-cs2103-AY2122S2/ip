package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event that occurs on a specific date.
 */
public class Event extends Task {

    private LocalDate startTime;


    /**
     * Initialises an Event task with a specified
     * description and start time.
     *
     * @param description the description of this Event task
     * @param time the time when this Event task starts
     */
    public Event(String description, LocalDate time) {
        super(description);
        this.startTime = time;
    }

    /**
     * Returns the start time of this Event task as a String
     * in yyyy-MM-dd format.
     *
     * @return the start time as a String in yyyy-MM-dd format
     */
    public String getStartTime() {
        return this.startTime.toString();
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
                .append(this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                .append(")");
        return sb.toString();
    }
}
