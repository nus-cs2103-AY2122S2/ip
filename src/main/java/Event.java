public class Event extends Task {
    protected String at;

    /**
     * Initializes a new event task
     * By default, the new task is set to "not done".
     * This task has a specific date as well as a start and end time.
     *
     * @param description Describes what needs to be done in this task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Prepends a checkbox "[E]" to the front of the task, which indicates the type of task.
     *
     * @return String containing a type icon that is prepended in front of the task description.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

}
