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

    protected static Task of(String type, String description) {

        String[] parameters;
        switch (type) {

            case "todo":
                return new ToDo(description);
            case "event":
                parameters = description.split(" /at ");
                return new Event(parameters[0], parameters[1]);
            case "deadline":
                parameters = description.split(" /by ");
                return new Deadline(parameters[0], parameters[1]);
            default:
                // Return null for invalid cases for now, will fix later.
                return null;
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
