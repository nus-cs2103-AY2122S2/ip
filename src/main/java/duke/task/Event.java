package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Event represents Event tasks.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Initializes the Event task with a task description, its regex, & boolean representing if it's done.
     * @param description String of task description.
     * @param at String of regex.
     * @param done boolean indicating if the task is done.
     * @throws DukeException error if the user's date input is incorrectly formatted.
     */
    public Event(String description, String at, boolean done) throws DukeException {
        super(description, done);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date was incorrectly formatted! Please format it as yyyy-mm-dd");
        }
    }

    /**
     * Overriden method to print the Event Task in a custom format.
     * @return A String representing the custom format of an Event Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * A getter method to print the Event Task in a custom format for saving to file.
     * @return A String representing the custom format of an Event Task.
     */
    @Override
    public String toStringSaveData() {
        return String.join(" | ", "E", String.valueOf(done ? 1 : 0), description, this.at.toString());
    }

}
