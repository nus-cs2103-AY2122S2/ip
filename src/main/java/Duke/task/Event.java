package Duke.task;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * a subclass of task
 */
public class Event extends Task implements Serializable {
    private LocalDate at;

    /**
     * Constructor
     * @param description task description
     * @param at date of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at);
    }

    /**
     * get the date of this task
     * @return LocalDate
     */
    public LocalDate getDate() {
        return this.at;
    }

    /**
     * String representation
     * @return String representation of this task
     */
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription()
                + "at (" + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
