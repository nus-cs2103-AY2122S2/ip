package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Inherits from the Task class.
 * Stores the description and deadline (date) of a Deadline object.
 * Provides to String method to print deadline details in specific format.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates an instance of a deadline object.
     * Sets isDone as false by default.
     *
     * @param description Description of the deadline task.
     * @param by Date by which the deadline task must be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Creates an instance of a deadline object.
     * Sets isDone based on the argument passed in.
     *
     * @param description Description of the deadline task.
     * @param isDone Boolean value of whether the deadline task is marked or unmarked.
     * @param by Date by which the deadline task must be completed by.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Overrides toString() method to print the deadline task in a specific format.
     * Calls the toString() method of the inherited task class.
     *
     * @return String to be printed in a specified format for the specific deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " - by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}