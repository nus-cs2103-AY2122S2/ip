package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class Deadline extends Task {

    protected LocalDate byDate;
    protected LocalTime byTime;

    /**
     * Constructor to create an deadline.
     *
     * @param description Description of task stored.
     * @param isDone      If the task is completed.
     * @param byDate      Due date of event occurring.
     * @param byTime      Due time of corresponding date.
     */
    public Deadline(String description, boolean isDone, LocalDate byDate, LocalTime byTime) {
        super(description, isDone);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * This method returns the type of this task.
     *
     * @return Returns the task type in String.
     */
    protected String getType() {
        return "D";
    }

    /**
     * This method formats the date and time of the event occurring in String.
     *
     * @return Returns formatted date and time in String format.
     */
    protected String getDateTime() {
        return byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " " + byTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * This method formats this event into a format that is used for saving.
     *
     * @return Returns formatted String to for saving to storage.
     */
    @Override
    public String formatToSave() {
        return super.formatToSave() + "|" + byDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " + byTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime() + ")";
    }
}