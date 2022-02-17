package ari.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task for Events
 */
public class EventTask extends Task implements Dateable {
    protected String date;
    protected LocalDate eventDate;

    /**
     * Constructor of EventTask
     *
     * @param description description of EventTask
     * @param eventTime   data and/or time of event
     * @param date        LocalDate representation for convenience
     */
    public EventTask(String description, String eventTime, LocalDate date) {
        super.taskDescription = description;
        this.date = eventTime;
        this.eventDate = date;
    }

    /**
     * Returns string representation of EventTask
     *
     * @return string representation of EventTask
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }

    /**
     * Returns string representation of EventTask in save file
     *
     * @return string representation of EventTask in save file
     */
    @Override
    public String writeToFile() {
        return "event " + super.writeToFile() + "/at " + eventDate.toString();
    }

    public LocalDate getDate() {
        return eventDate;
    }
}
