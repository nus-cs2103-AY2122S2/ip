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
     * The constructor for the Event.
     *
     * @param description The description of the task.
     * @param date The date the task should be completed at.
     * @param time The time the task should be complete at.
     * @param type The type of the task.
     */
    public Event(String description, LocalDate date, String time, String type) {
        super(description, type);
        this.date = date;
        this.time = time;
    }

    /**
     * Return a string representation.
     *
     * @return Return a string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " "  + time + ")\nTags: " + super.showTags();
    }
}