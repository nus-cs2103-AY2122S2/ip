package seedu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Inherits from the Task class.
 * Stores the description and date of an Event object.
 * Provides to String method to print event details in specific format.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Creates an instance of an event object.
     * Sets isDone as false by default.
     *
     * @param description Description of the event task.
     * @param at Date which the event task is to be done.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Creates an instance of an event object.
     * Sets isDone based on the argument passed in.
     *
     * @param description Description of the event task.
     * @param isDone Boolean value of whether the event task is marked or unmarked.
     * @param at Date which the event task is to be done.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDate.parse(at, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Retrieves the 'at' field of the event object in a specific format.
     *
     * @return Date of the event in the format dd-MM-yyyy.
     */
    public String getDate() {
        return at.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }


    /**
     * Formats the description of the event task to be written to a file.
     *
     * @return String format of event description.
     */
    public String toWrite() {
        return "[E]" + super.toString() + " /at " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Overrides toString() method to print the event task in a specific format.
     * Calls the toString() method of the inherited task class.
     *
     * @return String to be printed in a specified format for the specific event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
