package tasks;

import java.time.LocalDateTime;

/**
 * This class encapsulates a Between Task that needs to be done within a specific time frame.
 *
 * It has a start and end time.
 *
 * @author Ong Han Yang
 */
public class BetweenTask extends Task {
    /** start time of the task. */
    private LocalDateTime startTime;
    /** end time of the task. */
    private LocalDateTime endTime;

    /**
     * Constructs a Between Task.
     *
     * @param desc description for the task.
     * @param startTime the start/end time of the task.
     */
    public BetweenTask(String desc, LocalDateTime startTime, LocalDateTime endTime) {
        super(desc);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Produces a Between Task.
     *
     * @param desc description for the task.
     * @param startTime the start/end time of the task.
     * @return the Event Task.
     */
    public static BetweenTask of(String desc, LocalDateTime startTime, LocalDateTime endTime) {
        return new BetweenTask(desc, startTime, endTime);
    }

    /**
     * Represents this Between Task as a String.
     *
     * @return the String representation of an EventTask.
     */
    @Override
    public String toString() {
        boolean isSameDay = startTime.toLocalDate().equals(endTime.toLocalDate());
        return String.format("[B]%s (between: %s %s, and: %s)",
                super.toString(),
                startTime.toLocalDate(),
                startTime.toLocalTime(),
                isSameDay
                        ? endTime.toLocalTime()
                        : endTime.toLocalDate() + " " + endTime.toLocalTime());
    }
}
