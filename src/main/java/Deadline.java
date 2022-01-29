import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a deadline, which has to be done within a specific date/time.
 */
final class Deadline extends Task {

    private LocalDate dueTime;

    /**
     * Initializes a deadline with a name (description).
     *
     * @param description The name of the task.
     * @param dueTime     The due time for the deadline. The format should be 'YYYY-MM-DD'.
     */
    private Deadline(String description, String dueTime) {
        super(description);
        this.dueTime = LocalDate.parse(dueTime);
    }

    private Deadline(String[] details) {

        this(details[0], details[1]);
    }

    /**
     * The constructor to be called. Instantiates an event using a single string containing all the details.
     *
     * @param details Contains the description and time range for the deadline.
     */
    protected Deadline(String details) throws IllegalArgumentException {

        this(parseDetails(details));
    }

    /**
     * Parses the details containing the event description and due time.
     * <p>
     * (The details will be encapsulated in a separated class in the future if needed).
     *
     * @param details The string containing the event description and due time.
     * @return An array of strings containing the description and due time.
     */
    private static String[] parseDetails(String details) throws IllegalArgumentException {

        String[] args = details.split(" /by ", 2);

        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Oops, both of the description and the due time of the deadline can't be empty.");
        }

        return args;
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
        return super.toString() + " (by " +
                this.dueTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + ")";
    }
}
