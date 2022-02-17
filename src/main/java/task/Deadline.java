package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents Task which is entered with Deadline Prefix.
 */
public class Deadline extends Task {

    /**
     * Deadline date.
     */
    protected String by;
    /**
     * Creates a Deadline task.
     *
     * @param description Description of the Deadline task.
     * @param by Deadline Date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = formatDate(by);
    }

    /**
     * Creates Deadline based on saved data.
     *
     * @param status Status retrieved.
     * @param description Description retrieved.
     * @param tag Tag retrieved.
     * @param by Deadline retrieved.
     */
    public Deadline(String status, String description, String tag, String by) {
        super(status, description, tag);
        this.by = by;
    }

    /**
     * Returns the formatted Date to display from the deadline input.
     *
     * @param input the input Date as per "yyyy-MM-dd HH:mm"
     * @return the formatted Date as per "MMM-dd-yyyy HH:mm a"
     */
    private String formatDate(String input) {
        DateTimeFormatter formatIn = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatOut = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm a");
        return LocalDateTime.parse(input, formatIn).format(formatOut);
    }

    /**
     * Returns the string representation with details
     * on the task type, mark status, description and
     * deadline date.
     *
     * @return String representation of Task to display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string representation with details
     * on the task type, mark status, description and
     * deadline date.
     *
     * @return String representation of Task to save.
     */
    @Override
    public String toSave() {
        return "D" + super.toSave() + " : " + by;
    }
}
