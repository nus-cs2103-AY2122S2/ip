package mcbot.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class implements the Task class with additional event information. 
 */
public class Event extends Task {
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
    protected LocalDate eventDate;
    protected LocalTime eventTime;

    /**
     * Constructs an event task with a task name and event date. 
     *
     * @param taskName The name of the event.
     * @param eventDate The date of the event.
     */
    public Event(String taskName, LocalDate eventDate) {
        super(taskName);
        this.eventDate = eventDate;
    }

    /**
     * Constructs an Event task with a task name, date and time of the event.
     * 
     * @param taskName The name of the event.
     * @param eventDate The date of the event. 
     * @param eventTime The start time of the event
     */
    public Event(String taskName, LocalDate eventDate, LocalTime eventTime) {
        super(taskName);
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    /**
     * Returns the icon representing the task.
     * "E" is used to represent an event task.
     *
     * @return The task icon representation.
     */
    @Override
    public String getTaskIcon() {
        return "E";
    }

    /**
     * Returns a String to be stored into the data text file.
     * The format is E | 0 | taskName | date | time .
     * 0 represents incomplete task.
     * 1 represents completed task.
     *
     * @return The data representation as a String to be stored.
     */
    @Override
    public String toDataString() {
        String isDone = super.isMarked() ? "1" : "0";
        if (eventTime != null) {
            return getTaskIcon() + " | " + isDone + " | " + taskName 
                    + " | " + eventDate + " | " + eventTime;
        } else {
            return getTaskIcon() + " | " + isDone + " | " + taskName + " | " + eventDate;
        }
    }

    /**
     * Returns a String that describes the Task.
     * String contains the task icon, status icon, task name,
     * the date and time of the event. 
     *
     * @return The string describing the task.
     */
    @Override
    public String toString() {
        if (eventTime != null) {
            return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " +
                    super.taskName + " (at: " + dateFormatter.format(eventDate)
                    + " " + timeFormatter.format(eventTime) + ")";
        } else {
            return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " +
                    super.taskName + " (at: " + dateFormatter.format(eventDate) + ")";
        }
    }
}
