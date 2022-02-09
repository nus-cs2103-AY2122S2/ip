package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

/**
 * Deadline are tasks that need to be done before a specific date/time.
 *
 * <p>e.g., submit report by 11/10/2019 5pm.</>
 */
public class Deadline extends Task {
    private static final char DEADLINE_SYMBOL = 'D';
    private static final String DATE_FORMAT = "MMM d yyyy";
    private static final String TIME_FORMAT = "hh:mm a";

    private static final String DEFAULT_DATE = "2020-12-12";
    private static final String DEFAULT_TIME = "11:59 PM";

    private LocalDate deadlineDate;
    private LocalTime deadlineTime;

    /**
     * Default constructor for Deadline.
     *
     * <p>Calls super class, Task, default constructor.
     * Sets deadlineDate and deadlineTime to default values 2020-12-12 and 2359.</>
     */
    public Deadline() {
        super();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        this.deadlineDate = LocalDate.parse(DEFAULT_DATE); // just get default date
        this.deadlineTime = LocalTime.parse(DEFAULT_TIME, timeFormatter);
    }

    /**
     * Overloaded constructor for Deadline.
     *
     * @param taskDescription Description of deadline task.
     * @param deadlineDate Date of deadline.
     * @param deadlineTime Time of deadline.
     */
    public Deadline(String taskDescription, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(taskDescription);

        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns Deadline data in a defined save file string format.
     *
     * @return A string with Deadline data in the defined save file format: <br>
     * D|true/false|taskDescription|MMM d yyyy|hh:mm a \n
     */
    @Override
    public String saveFileFormat() {
        return DEADLINE_SYMBOL + "|" + this.isDone + "|" + taskDescription + "|" 
                + this.deadlineDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + "|" 
                + this.deadlineTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT)) + "\n";
    }

    /**
     * Extracts and initializes data to deadline from a string.
     *
     * @param data The string to extract data and initialize data from.
     *             Data format: D|true/false|taskDescription|MMM d yyyy|hh:mm a
     */
    @Override
    public void extractDataFromLine(String data) {
        StringTokenizer st = new StringTokenizer(data, "|");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        st.nextToken(); // remove the type symbol
        isDone = Boolean.parseBoolean(st.nextToken());
        taskDescription = st.nextToken();
        deadlineDate = LocalDate.parse(st.nextToken(), dateFormatter);
        deadlineTime = LocalTime.parse(st.nextToken(), timeFormatter);
    }

    /**
     * Gets the Deadline information in string format.
     *
     * @return Deadline information in the following string format:
     * [D][ ] taskDescription (by: date time)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT))
                + " " + this.deadlineTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT)) + ")";
    }
}
