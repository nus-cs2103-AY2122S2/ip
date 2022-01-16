/**
 * A task represents an instruction inputted to Duke by a user.
 * At this stage, a task has minimally a name (description).
 */
class Task {
    private String description;

    /**
     * Constructor of a task.
     *
     * @param description the description of the task as inputted by the user.
     */
    protected Task(String description) {
        this.description = description;
    }

    /**
     * @return the description of the task.
     */
    protected String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the message of the task (when it is finished).
     *
     * @return the message.
     */
    protected String getMessage() {
        return this.description;
    }

    /**
     * Performs the associated action of the task. By default, there is no action associated to a task.
     */
    protected void act() {
        return;
    }
}
