/**
 * Represents a deadline, which has to be done within a specific date/time.
 */
final class Deadline extends Task {

    // At this stage, the due time is treated as a string.
    private String dueTime;

    /**
     * Initializes a deadline with a name (description).
     *
     * @param description The name of the task.
     * @param dueTime The due time for the deadline.
     */
    protected Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = dueTime;
    }


    @Override
    protected String getTypeIcon() {
        return "[D]";
    }

    /**
     * Converts a deadline to a string.
     *
     * @return The string that represents the deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by " + this.dueTime + ")";
    }
}
