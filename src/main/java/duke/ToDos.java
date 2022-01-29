package duke;

/**
 * Represents a To-Do Task created by a user, modelled by an Object.
 */
public class ToDos extends Task {

    /**
     * The constructor for creating a To-Do task.
     *
     * @param description the content of what the deadline is about.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * The constructor for creating an To-Do task.
     *
     * @param description the content of what the event is about.
     * @param hasCompleted a boolean variable to indicate if the task has been completed.
     */
    public ToDos(String description, boolean hasCompleted) {
        super(description);
        this.hasCompleted = hasCompleted;
    }

    /**
     * This method returns a string of the type of task this is.
     * @return a string that indicates what kind of task this is.
     */
    @Override
    public String toString() {
        return "[" + Type.T + "]";
    }

}