package echo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class inherits from Task class and encapsulates a event task.
 */
public class EventTask extends Task {

    /** Duration of event. */
    private final LocalDateTime AT;

    /**
     * Constructor for EventTask class.
     *
     * @param desc Description of EventTask.
     * @param at Duration of EventTask.
     */
    public EventTask(String desc, LocalDateTime at) {
        super(desc);
        this.AT = at;
    }

    /**
     * String representation of EventTask for saving.
     *
     * @return String representation of EventTask for saving.
     */
    @Override
    public String saveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
        return "E | " + super.saveFormat() + " | " + this.AT.format(format);
    }

    /**
     * String representation of EventTask.
     *
     * @return String representation of EventTask.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E]" + super.toString() + " (at: " + this.AT.format(formatDateTime) + ")";
    }
}
