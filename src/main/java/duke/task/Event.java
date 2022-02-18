package duke.task;

import java.util.HashMap;

/**
 * Represents an event, which has a start time and end time.
 */
final class Event extends Task {

    private static final String TIME_RANGE_FIELD = "time_range";
    private static final String DELIMITER = " /at ";
    private static final String TASK_TYPE_ICON = "[E]";

    // At this stage, the time range is treated as a string.
    private final String timeRange;
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
     * Constructs an <code>task.Event</code>.
     * Instantiates an event using a single string containing all the details.
     *
     * @param details Contains the description and time range for the event.
     * @throws FailedToInterpretTaskException If failed to interpret the task as an event.
     */
    protected Event(String details) throws FailedToInterpretTaskException {

        this(parseDetails(details));
    }

    protected Event(HashMap<String, Object> infoTable) {
        super(infoTable);
        this.timeRange = (String) infoTable.get(TIME_RANGE_FIELD);
    }


    /**
     * Parses the details containing the event description and time range.
     * <p>
     * (The details will be encapsulated in a separated class in the future if needed).
     *
     * @param details The string containing the event description and time range.
     * @return An array of strings containing the description and time range.
     */
    private static String[] parseDetails(String details) throws FailedToInterpretTaskException {

        String[] args = details.split(DELIMITER, 2);

        if (args.length != 2) {
            throw new FailedToInterpretTaskException(
                    "Oops, both of the description and the time of the event can't be empty.");
        }

        return args;
    }

    /**
     * Turns the current <code>task.Task</code> into a <code>HashMap</code> that
     * contains the necessary information to be written into hard disk.
     *
     * @return A table contains the information.
     */
    public HashMap<String, Object> getInfoTable() {

        var infoTable = this.initializeInfoTable();
        infoTable.put(TIME_RANGE_FIELD, this.timeRange);
        return infoTable;
    }

    /**
     * Returns the icon that represents the type of the task.
     *
     * @return The icon.
     */
    @Override
    protected String getTypeIcon() {
        return TASK_TYPE_ICON;
    }

    /**
     * Returns the type of the current task.
     *
     * @return The type of the task.
     */
    @Override
    protected TaskType getType() {
        return TaskType.EVENT;
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


