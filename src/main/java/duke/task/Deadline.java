package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Deadline represents deadline tasks. 
 */
public class Deadline extends Task {

    protected LocalDate by;
    
    /**
     * Initializes the Deadline task with a task description, its regex & a boolean indicating if it's done.
     * @param description String of task description. 
     * @param by String of regex. 
     * @param done boolean indicating if the task is done.  
     * @throws DukeException error if the user's date input is incorrectly formatted. 
     */
    public Deadline(String description, String by, boolean done) throws DukeException {
        super(description, done);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date was incorrectly formatted! Please format it as yyyy-mm-dd");
        }
    }

    /**
     * Overriden method to print the Deadline Task in a custom format.
     * @return A String representing the custom format of a Deadline Task. 
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * A getter method to print the Deadline Task in a custom format for saving to file. 
     * @return A String representing the custom format of a Deadline Task. 
     */
    @Override
    public String toStringSaveData() {
        return String.join(" | ", "D", String.valueOf(done ? 1 : 0), description, this.by.toString());
    }

}
