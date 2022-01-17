/**
 * Represent a task that is stored by Duke.
 */
abstract class Task {

    private boolean isDone;
    private String description;

    /**
     * Constructor of a task. Sets the description of the task, and set the isDone status to be false by default.
     *
     * @param description The description of the task.
     */
    protected Task(String description) {

        this.description = description;
        this.isDone = false;
    }

    /**
     * Factory method. Parses the instruction and determines the type of task to be instantiated.
     *
     * @param instruction The line of instruction used to create a task.
     * @return The instantiated task.
     * @throws IllegalArgumentException If the instruction (i) does not contain valid type of task; or (ii) does not
     * contain a valid description of task.
     */
    protected static Task of(String instruction) throws IllegalArgumentException {

        String[] args = instruction.split(" ", 2);
        String type = args[0];

        if (args.length < 2) {

            throw new IllegalArgumentException("Oops, a type and a description for the task must be provided.");
        }

        // Contains the description and possibly other information about the task.
        String details = args[1];

        switch (type) {

            case "todo":
                return new ToDo(details);
            case "event":
                return new Event(details);
            case "deadline":
                return new Deadline(details);
            default:
                // Invalid type.
                throw new IllegalArgumentException("Oops, the type of the task must be todo/event/deadline.");
        }
    }

    /**
     * Marks the current task as done.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as not done.
     */
    protected void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Obtains the status of the task.
     *
     * @return True if the task is done already, false otherwise.
     */
    protected boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + this.description;
    }

    /**
     * Gets the icon that represents the status of the task.
     *
     * @return The icon.
     */
    private String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    protected abstract String getTypeIcon();

}
