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
     * Returns string representation of EventTask for saving.
     *
     * @return String representation of EventTask for saving.
     */
    @Override
    public String saveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
        return "E | " + super.saveFormat() + " | " + this.AT.format(format);
    }

    /**
     * Returns string representation of EventTask.
     *
     * @return String representation of EventTask.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[E]" + super.toString() + " (at: " + this.AT.format(formatDateTime) + ")";
    }

    /**
     * Checks if instances of EventCommand are equal.
     *
     * @param obj Object.
     *
     * @return If the DESCRIPTION and DATE_TIME are equal, returns true; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventTask) {
            // Since obj is an instanceof EventCommand, it is safe to type cast
            // Object to EventCommand.
            EventTask eventTask = (EventTask) obj;
            boolean isDescriptionEqual = super.equals(eventTask);
            boolean isDateTimeEqual = this.AT.equals(eventTask.AT);
            return isDescriptionEqual && isDateTimeEqual;
        }
        return false;
    }
}
