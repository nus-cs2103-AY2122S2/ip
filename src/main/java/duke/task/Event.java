package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class Event extends Task {

    protected LocalDate atDate;
    protected LocalTime atTime;

    /**
     * Constructor to create an event.
     *
     * @param description Description of task stored.
     * @param isDone      If the task is completed.
     * @param atDate      Date of event occurring.
     * @param atTime      Time of event occurring.
     */
    public Event(String description, boolean isDone, LocalDate atDate, LocalTime atTime) {
        super(description, isDone);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    /**
     * This method returns the type of this task.
     *
     * @return Returns the task type in String.
     */
    public String getType() {
        return "E";
    }

    /**
     * This method formats the date and time of the event occurring in String.
     *
     * @return Returns formatted date and time in String format.
     */
    protected String getDateTime() {
        return atDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + atTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * This method formats this event into a format that is used for saving.
     *
     * @return Returns formatted String to for saving to storage.
     */
    @Override
    public String formatToSave() {
        return super.formatToSave() + "|" + atDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " + atTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateTime() + ")";
    }
}