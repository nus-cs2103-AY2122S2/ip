package duke.task;

/**
 * A type of Task, which is to be done at somewhere.
 */
public class Event extends Task {
    protected String at;

    /**
     * Creates an Event task.
     *
     * @param description description of Event task.
     * @param at where the Event Task is at.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates an Event task.
     *
     * @param description description of Event task.
     * @param at where the Event Task is at.
     * @param isDone status of Task, whether done or not.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getTaskData() {
        return super.getTaskData() + " | " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
}
