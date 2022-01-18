/**
 * A event task that has a deadline property.
 */
public class EventTask extends Task {
    protected String deadline;

    /**
     * Constructs a new event task from the description
     * and the deadline.
     *
     * @param description description of the task
     * @param deadline deadline of the task
     */
    public EventTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return the string representation of the event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(by: " + this.deadline + ")";
    }
}
