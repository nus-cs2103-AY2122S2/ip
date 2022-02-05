package duke.task;

/**
 * Represents an event created by the user that are/will be stored in the database.
 * An <code>Events</code> inherits from <code>Tasks</code> and is represented by its
 * name, completion status, and timing of the event e.g.,
 * <code>"Eat lunch", true, "9am"</code>
 */
public class Events extends Tasks {
    String timing; // Timing of event

    // Constructor
    public Events(String taskName, String timing) {
        super(taskName);
        this.timing = timing;
    }

    public Events(String taskName, boolean completion, String timing) {
        super(taskName, completion);
        this.timing = timing;
    }

    // Get timing of event
    public String getTiming() {
        return "(at: " + timing + ")";
    }

    /**
     * Returns a new completed instance of the task.
     *
     * @return a new instance of the task that has been completed.
     */
    @Override
    public Events completeTask() {
        return new Events(super.getName(), true, timing);
    }

    /**
     * Returns a new uncompleted instance of the task.
     *
     * @return a new instance of the task that has not been completed.
     */
    @Override
    public Events uncompleteTask() {
        return new Events(super.getName(), false, timing);
    }

    // Format of saving to database
    public String toDatabaseString() {
        return "E | " + (this.getCompletion() ? "X" : " ")
                + " | " + super.getName() + " | " + timing + "\n";
    }

    // toString returning event
    public String toString() {
        return "[E][" + (this.getCompletion() ? "X" : " ") + "] "
                + super.getName() + " (at: " + timing + ")";
    }
}