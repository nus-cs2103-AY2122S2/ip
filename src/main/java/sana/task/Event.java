package sana.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the Event sana.task
 *
 * @author Jan
 * @version 1.0
 */
public class Event extends Task {
    /** The time of this event */
    private LocalDate eventTime;

    /**
     * Constructor for Event objects
     *
     * @param eventName  the event name
     * @param eventTime  time of the event
     */
    public Event(String eventName, LocalDate eventTime) {
        super(eventName);
        this.eventTime = eventTime;
    }

    /**
     * Constructor for Event objects
     *
     * @param eventName the event name
     * @param isDone    if the event is done
     * @param eventTime the time of the event
     */
    public Event(String eventName, boolean isDone, String eventTime) {
        super(eventName, isDone);
        this.eventTime = LocalDate.parse(eventTime);
    }

    /**
     * Returns a String representation of the Event
     *
     * @return  Event in String
     */
    @Override
    public String toStringFromList() {
        String box1 = "[E]";
        String doneness;
        if (super.isDone()) {
            doneness = "[X] ";
        } else {
            doneness = "[ ] ";
        }
        String eventName = super.toString();
        String time = " (at: " + eventTimeToString() + ")\n";
        return box1 + doneness + eventName + time;
    }

    /**
     * Converts the strings in mem to an Event
     *
     * @param taskStr   the string in mem
     */
    public static Event memToTask(String taskStr) {
        // Example str = E|0|return book|June 6th
        String[] args = taskStr.split(":");
        boolean isDone = args[1].equals("1");
        return new Event(args[2], isDone, args[3]);
    }

    /**
     * Converts Event into a string to be stored in mem
     *
     * @return string of event to be stored in mem
     */
    @Override
    public String taskToMemStr() {
        // Example str = E|0|return book|June 6th
        StringBuilder sb = new StringBuilder();
        sb.append("E:");
        if (this.isDone()) {
            sb.append("1:");
        } else {
            sb.append("0:");
        }
        sb.append(super.toString());
        sb.append(":");
        sb.append(this.eventTime);
        return sb.toString();
    }
    /**
     * Returns a string representation of the event time
     *
     * @return  event time in string
     */
    private String eventTimeToString() {
        return eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
