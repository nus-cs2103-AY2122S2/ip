package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.HashMap;

/**
 * Represents a deadline, which has to be done within a specific date/time.
 */
final class Deadline extends Task {

    private static final String DUE_TIME_FIELD = "due_time";
    private static final String DELIMITER = " /by ";
    private static final String TASK_TYPE_ICON = "[D]";

    private final LocalDate dueTime;

    /**
     * Initializes a deadline with a name (description).
     *
     * @param description The name of the task.
     * @param dueTime     The due time for the deadline. The format should be 'YYYY-MM-DD'.
     */
    private Deadline(String description, String dueTime) throws FailedToInterpretTaskException {
        super(description);
        try {
            this.dueTime = LocalDate.parse(dueTime);
        } catch (DateTimeParseException e) {
            throw new FailedToInterpretTaskException("Oops, the format of date should be YYYY-MM-DD!");
        }
    }


    private Deadline(String[] details) throws FailedToInterpretTaskException {

        this(details[0], details[1]);
    }

    /**
     * The constructor to be called. Instantiates an event using a single string containing all the details.
     *
     * @param details Contains the description and time range for the deadline.
     * @throws FailedToInterpretTaskException If unable to interpret the string as a deadline.
     */
    protected Deadline(String details) throws FailedToInterpretTaskException {

        this(parseDetails(details));
    }

    /**
     * Constructs a <code>Deadline</code> task with a hash map that contains the necessary information.
     *
     * @param infoTable The hash map with the necessary information.
     */
    public Deadline(HashMap<String, Object> infoTable) {
        super(infoTable);
        this.dueTime = (LocalDate) infoTable.get(DUE_TIME_FIELD);
    }

    /**
     * Parses the details containing the event description and due time.
     * <p>
     * (The details will be encapsulated in a separated class in the future if needed).
     *
     * @param details The string containing the event description and due time.
     * @return An array of strings containing the description and due time.
     */
    private static String[] parseDetails(String details) throws FailedToInterpretTaskException {

        String[] args = details.split(DELIMITER, 2);

        if (args.length != 2) {
            throw new FailedToInterpretTaskException(
                    "Oops, both of the description and the due time of the deadline can't be empty.");
        }

        return args;
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
        return TaskType.DEADLINE;
    }


    /**
     * Turns the current <code>task.Task</code> into a <code>HashMap</code>
     * that contains the necessary information to be written into hard disk.
     *
     * @return The HashMap containing the information about the current <code>task.Task</code>.
     */
    @Override
    public HashMap<String, Object> getInfoTable() {

        var infoTable = this.initializeInfoTable();
        infoTable.put(DUE_TIME_FIELD, this.dueTime);
        return infoTable;
    }

    /**
     * Converts a deadline to a string.
     *
     * @return The string that represents the deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by "
                + this.dueTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ")";
    }
}
