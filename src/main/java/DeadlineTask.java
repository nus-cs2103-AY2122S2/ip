/**
 * A deadline task that has a deadline property.
 */
public class DeadlineTask extends Task {
    /**
     * The deadline of the task.
     */
    protected String deadline;

    /**
     * Constructs a new deadline task from the description
     * and the deadline.
     *
     * @param description description of the task
     * @param deadline deadline of the task
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return the string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + this.deadline + ")";
    }
}
