package duke;

/**
 * A type of Task that represents a task that must be done before a certain date.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + description + " (by: " + by + ")";
    }
}