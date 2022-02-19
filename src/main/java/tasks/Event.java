package tasks;

/**
 * Represents work that occurs during a given duration.
 */
public class Event extends Task {
    protected String at;

    /**
     * Returns an Event object that contains the details of the
     * work that occurs during a given duration.
     *
     * @param description describes the work that occurs during a given duration.
     * @param at the duration of the work.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    private Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the duration of the current event.
     *
     * @return The duration of the current event.
     */
    public String getAt() {
        return this.at;
    }

    /**
     * Duplicates the current Event task.
     *
     * @return A duplicate of the current Event task.
     */
    @Override
    public Event clone() {
        return new Event(super.description, this.at, super.isDone);
    }

    /**
     * Describes the current Event task with a prefix indicating its task type,
     * as well as the duration of the event.
     *
     * @return A string representation of the current Event task's description,
     * completion status, and its duration.
     */
    @Override
    public String toString() {
        final String taskDescription = "[E]" + super.toString();
        return taskDescription + " (at: " + this.at + ")";
    }
}
