package funbox.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class which inherits from Task.
 */
public class Event extends Task {

    public LocalDate date;
    public String time;

    /**
     * The constructors for event class.
     *
     * @param description The description of task sent by the user.
     * @param date The date which the task should be completed by.
     * @param time The time which the task should be completed by.
     * @param type The type of the task.
     */
    public Event(String description, LocalDate date, String time, String type) {
        super(description, type);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " "  + time + ")";
    }
}