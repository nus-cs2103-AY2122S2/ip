/**
 * This class encapsulates a Deadline task which inherits from Task.
 */
public class Deadline extends Task {
    private String by; //date/time that the task needs to be done by.

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}
