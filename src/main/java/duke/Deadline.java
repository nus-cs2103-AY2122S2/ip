package duke;

import java.time.LocalDate;

/**
 * Represents a task that is a Deadline to be met. 
 */
public class Deadline extends Task {
    /**
     * Constructor for the Deadline object. Takes in the description of the task and the due date.
     * 
     * @param description a string that describes the deadline.
     * @param by a LocalDate object that represents the due date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description, by);
    }

    /**
     * Converts the deadline to a string that contains the status of the deadline, the description, and the due date.
     */
    @Override
    public String toString() {
        String deadlineString = "[D]" + this.getStatusIcon() + " " +
                this.description + " (by: " + Date.toString(this.date) + ")";
        return deadlineString;
    }
}