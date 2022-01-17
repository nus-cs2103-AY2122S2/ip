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
     * @param timeRange   The range of time associated with the event.
     */
    private Event(String description, String timeRange) {
        super(description);
        this.timeRange = timeRange;
    }

    private Event(String[] details) {

        this(details[0], details[1]);
    }

    /**
     * The constructor to be called. Instantiates an event using a single string containing all the details.
     *
     * @param details Contains the description and time range for the event.
     */
    protected Event(String details) throws IllegalArgumentException {

        this(parseDetails(details));
    }

    @Override
    protected String getTypeIcon() {
        return "[E]";
    }

    /**
     * Parses the details containing the event description and time range.
     * <p>
     * (The details will be encapsulated in a separated class in the future if needed).
     *
     * @param details The string containing the event description and time range.
     * @return An array of strings containing the description and time range.
     */
    private static String[] parseDetails(String details) throws IllegalArgumentException {

        String[] args = details.split(" /at ", 2);

        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Oops, both of the description and the time of the event can't be empty.");
        }

        return args;
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


