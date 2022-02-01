package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a class which represents the deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a Deadline task object, constructor.
     *
     * @param description A description of the task.
     * @param by The date which the task must be completed.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats the description of the task into a standardised format to
     * be saved to a file.
     *
     * @return A String which is to be saved in a file.
     */
    @Override
    public String formatString() {
        String output = "D";
        String markState = this.isDone ? "mark" : "unmark";
        return output + " | " + markState + " | "
                + this.description + this.by;
    }

    /**
     * Returns the String representation of the deadline task.
     *
     * @return A String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
