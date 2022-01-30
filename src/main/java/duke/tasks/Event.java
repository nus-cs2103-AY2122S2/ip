package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class is a class which represents the event task.
 */
public class Event extends Task{
    protected LocalDate at;

    /**
     * Creates an Event task object, constructor.
     * @param description A description of the task.
     * @param at The date which the event happens.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Formats the description of the task into a standardised format to
     * be saved to a file.
     *
     * @return A String which is to be saved in a file.
     */
    @Override
    public String formatString() {
        String output = "E";
        String markState = this.isDone ? "mark" : "unmark";
        return output + " | " + markState + " | "
                + this.description + " | " + this.at;

    }

    /**
     * Returns the String representation of the Event task.
     *
     * @return A String representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                this.at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
