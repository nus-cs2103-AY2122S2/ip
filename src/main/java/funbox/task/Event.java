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
     * The constructor class for Event
     * @param description The description of task sent by the user
     * @param by The deadline which the task should be completed by
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