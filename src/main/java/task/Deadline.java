package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents Task which is entered with Deadline Prefix.
 */
public class Deadline extends Task {

    /** Deadline date.*/
    protected String by;

    /**
     * Instantiates a deadline task with task description and deadline.
     *
     * @param description Description of the deadline task.
     * @param by Deadline Date.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = formatDate(by);
    }

    /**
     * Instantiates a deadline task with details of the task retrieved
     * from the stored file.
     *
     * @param data Array containing details of deadline task from stored file.
     */
    public Deadline(String[] data) {
        super(data[1],data[2],data[3]);
        this.by = data[4];
    }

    /**
     * Returns the formatted date to display from the deadline input.
     *
     * @param input Input Date as per "yyyy-MM-dd HH:mm"
     * @return Formatted Date as per "MMM-dd-yyyy HH:mm a"
     */
    private String formatDate(String input) {
        DateTimeFormatter formatIn = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatOut = DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm a");
        return LocalDateTime.parse(input, formatIn).format(formatOut);
    }

    /**
     * Returns the string representation with details
     * on the task type, task status, task description and
     * deadline date.
     *
     * @return String representation of deadline task to be displayed.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the string representation with details
     * on the task type, task status, task description and
     * deadline date.
     *
     * @return String representation of deadline task to be stored.
     */
    @Override
    public String toSave() {
        return "D" + super.toSave() + " : " + by;
    }
}
