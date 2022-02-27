package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Event represents Event tasks.
 */
public class Event extends Task {

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
            super.date = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date was incorrectly formatted! Please format it as yyyy-mm-dd");
        }
    }

    /**
     * Getter method to return the description of an Event.
     * @return A String representing the description of an Event.
     */
    @Override
    public String getDescription() {
        return this.description + ", " + super.date.toString();
    }

    /**
     * Overriden method to print the Event Task in a custom format.
     * @return A String representing the custom format of an Event Task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + super.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * A getter method to print the Event Task in a custom format for saving to file.
     * @return A String representing the custom format of an Event Task.
     */
    @Override
    public String toStringSaveData() {
        return String.join(" | ", "E",
                String.valueOf(done ? 1 : 0), description, super.date.toString());
    }

}
