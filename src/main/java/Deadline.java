/**
 * The Deadline class.
 * @author Jet Tan
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructor for a new instance of Deadline, containing the description and deadline of the task.
     *
     * @param description The description of the Task.
     * @param by The desired deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string containing task type, done status and description along with deadline.
     *
     * @return string containing task type, done status and description along with deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}