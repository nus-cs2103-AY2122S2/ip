package chatbot.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructs a deadline with the specified description, start date, start time, end date and end time.
     * @param desc the description of the event
     * @param date the due date of the event
     * @param time the due time of the event
     */
    public Deadline(String desc, LocalDate date, LocalTime time) {
        super(desc);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() + " " + time.toString() + ")";
    }
}