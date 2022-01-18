public class Deadline extends Task {
    protected String by;

    /**
     * Initializes a new instance of task that has a deadline
     * By default, the new task is set to "not done".
     *
     * @param description Describes what needs to be done in this task.
     * @param by Specifies the date/time that this task has to be done by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Prepends a checkbox "[D]" to the front of the task, which indicates the type of task.
     *
     * @return String containing a type icon that is prepended in front of the task description.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
