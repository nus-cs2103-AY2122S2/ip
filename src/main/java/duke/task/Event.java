package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task. An Event Object corresponds to a String description of the event
 * and String timing of which the event is occuring at.
 */
public class Event extends Task implements Comparable<Task> {
    private final LocalDateTime dateAndTime;

    /**
     * Constructs an event task.
     * @param description Description of event task.
     * @param dateAndTime Date and time string of event task.
     */
    public Event(String description, LocalDateTime dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }
    /**
     * Compare tasks by task type and local date time.
     * @param otherTask The task compared with.
     * @return Returns -1,0,1.
     */
    @Override
    public int compareTo(Task otherTask) {
        if (otherTask.getClass() != this.getClass()) {
            int compareValueOfOtherTask = 3;
            int compareValueOfCurrentTask = 3;
            if (otherTask instanceof ToDo) {
                compareValueOfOtherTask = 1;
            }
            if (otherTask instanceof Deadline) {
                compareValueOfOtherTask = 2;
            }
            return Integer.compare(compareValueOfCurrentTask, compareValueOfOtherTask);
        } else {
            Event otherEvent;
            otherEvent = (Event) otherTask;
            LocalDateTime currentDateAndTime = this.dateAndTime;
            LocalDateTime otherDateAndTime = otherEvent.dateAndTime;
            return compareLocalDateTime(currentDateAndTime, otherDateAndTime);
        }
    }
    private static int compareLocalDateTime(LocalDateTime currentDateAndTime, LocalDateTime otherDateAndTime) {
        if (currentDateAndTime.isBefore(otherDateAndTime)) {
            return -1;
        } else if (currentDateAndTime.isAfter(otherDateAndTime)) {
            return 1;
        } else {
            return 0;
        }
    }
    /**
     * Returns a String representation of an Event task in the desired format.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" MMM dd yyyy h:mm a");
        String outputDateTime = dateAndTime.format(outputFormat);
        return "[E]" + super.toString() + " (at:" + outputDateTime + ")";
    }
}
