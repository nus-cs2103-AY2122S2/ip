/**
 * Represents an event, which has a start time and end time.
 */
final class Event extends Task {
    // At this stage, the time range is treated as a string.
    private String timeRange;

    /**
     * Initializes an event with a name (description) and a time range.
     *
     * @param description The name of the task.
     * @param timeRange The range of time associated with the event.
     */
    protected Event(String description, String timeRange) {
        super(description);
        this.timeRange = timeRange;
    }

    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    /**
     * Converts an event to a string.
     *
     * @return The string that represents the event.
     */
    @Override
    public String toString() {
        return super.toString() + " (at " + this.timeRange + ")";
    }
}


