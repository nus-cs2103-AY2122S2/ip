package tasks;

import java.time.LocalDateTime;

/**
 * This class encapsulates an Event Task that occurs at a specific time frame.
 *
 * It has a start and end time.
 *
 * @author Ong Han Yang
 */
public class EventTask extends Task {
    /** start time of the event. */
    private LocalDateTime startTime;
    /** end time of the event. */
    private LocalDateTime endTime;

    /**
     * Constructs an Event Task.
     *
     * @param desc description for the task.
     * @param startTime the start/end time of the task.
     */
    public EventTask(String desc, LocalDateTime startTime, LocalDateTime endTime) {
        super(desc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Produces an Event Task.
     *
     * @param desc description for the task.
     * @param startTime the start/end time of the task.
     * @return the Event Task.
     */
    public static EventTask of(String desc, LocalDateTime startTime, LocalDateTime endTime) {
        return new EventTask(desc, startTime, endTime);
    }

    /**
     * Represents this EventTask as a String.
     *
     * @return the String representation of an EventTask.
     */
    @Override
    public String toString() {
        boolean isSameDay = startTime.toLocalDate().equals(endTime.toLocalDate());
        return String.format("[E]%s (at: %s %s, until: %s)",
                super.toString(),
                startTime.toLocalDate(),
                startTime.toLocalTime(),
                isSameDay
                        ? endTime.toLocalTime()
                        : endTime.toLocalDate() + " " + endTime.toLocalTime());
    }
}
