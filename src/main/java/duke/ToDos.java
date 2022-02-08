package duke;

/**
 * Represents a To-Do Task created by a user, modelled by an Object.
 */
public class ToDos extends Task {

    /**
     * The constructor for creating a To-Do task.
     *
     * @param description the content of what the deadline is about.
     * @param priority the priority level of this task (HIGH, MEDIUM, LOW).
     */
    public ToDos(String description, Priority priority) {
        super(description);
        this.priority = priority;
    }

    /**
     * The constructor for creating an To-Do task.
     *
     * @param description the content of what the event is about.
     * @param hasCompleted a boolean variable to indicate if the task has been completed.
     * @param priority the priority level of this task (HIGH, MEDIUM, LOW).
     */
    public ToDos(String description, boolean hasCompleted, Priority priority) {
        super(description);
        this.hasCompleted = hasCompleted;
        this.priority = priority;

    }

    /**
     * This method returns a string of the type of task this is.
     * @return a string that indicates what kind of task this is.
     */
    @Override
    public String toString() {
        return "[T]";
    }

}
