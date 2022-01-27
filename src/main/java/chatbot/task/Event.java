package chatbot.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalTime startTime;
    private final LocalDate endDate;
    private final LocalTime endTime;

    /**
     * Constructs an event with the specified description, start date, start time, end date and end time.
     *
     * @param desc      the description of the event
     * @param startDate the start date of the event
     * @param startTime the start time of the event
     * @param endDate   the end date of the event
     * @param endTime   the end time of the event
     */
    public Event(String desc, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(desc);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + startDate.toString() + " " + startTime.toString() + " to "
                + endDate.toString() + " " + endTime.toString() + ")";
    }
}