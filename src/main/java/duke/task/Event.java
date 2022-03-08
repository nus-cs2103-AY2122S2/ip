package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import duke.exception.DukeException;

/**
 * Event are tasks that start at a specific time and ends at a specific time.
 *
 * <p>e.g., team project meeting on 2/10/2019 2-4pm.</p>
 */
public class Event extends Task {
    public static final char EVENT_SYMBOL = 'E';
    private static final String DATE_FORMAT = "MMM d yyyy";
    private static final String TIME_FORMAT = "hh:mm a";

    private static final String DEFAULT_DATE = "2020-12-12";
    private static final String DEFAULT_TIME = "11:59 PM";

    private static final String PARSE_FILE_ERROR = "Error reading event data from file";

    private LocalDate eventDate;
    private LocalTime eventTime;

    /**
     * Default constructor for Event.
     *
     * <p>Calls super class, Task, default constructor.
     * Sets eventDate and eventTime to default values 2020-12-12 and 2359.</p>
     */
    public Event() {
        super();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        this.eventDate = LocalDate.parse(DEFAULT_DATE); // just get default date
        this.eventTime = LocalTime.parse(DEFAULT_TIME, timeFormatter);
    }

    /**
     * Overloaded constructor for Event.
     *
     * @param taskDescription Description of event.
     * @param eventDate Date of event.
     * @param eventTime time of event.
     */
    public Event(String taskDescription, LocalDate eventDate, LocalTime eventTime) {
        super(taskDescription);

        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    /**
     * Returns event data in a defined save file string format.
     *
     * @return A string with Deadline data in the defined save file format: <br>
     * E|true/false|taskDescription|MMM d yyyy|hh:mm a \n
     */
    @Override
    public String saveFileFormat() {
        return EVENT_SYMBOL + "|" + getIsDone() + "|" + getTaskDescription() + "|" + this.eventDate.format(
                DateTimeFormatter.ofPattern(DATE_FORMAT)) + "|" + this.eventTime.format(
                DateTimeFormatter.ofPattern(TIME_FORMAT)) + "\n";
    }

    /**
     * Extracts and initializes data to deadline from a string.
     *
     * @param data The string to extract data and initialize data from.
     *             Data format: E|true/false|taskDescription|MMM d yyyy|hh:mm a
     * @throws DukeException If there are issues extracting data from line.
     */
    @Override
    public void extractDataFromLine(String data) throws DukeException {
        StringTokenizer st = new StringTokenizer(data, "|");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        try {
            st.nextToken(); // remove the type symbol
            setIsDone(Boolean.parseBoolean(st.nextToken()));
            setTaskDescription(st.nextToken());
            eventDate = LocalDate.parse(st.nextToken(), dateFormatter);
            eventTime = LocalTime.parse(st.nextToken(), timeFormatter);
        } catch (DateTimeParseException | NoSuchElementException execption) {
            throw new DukeException(PARSE_FILE_ERROR);
        }
    }

    /**
     * Gets the Event information in string format.
     *
     * @return Event information in the following string format:
     * [E][ ] taskDescription (at: date time)
     */
    @Override
    public String toString() {
        String date = eventDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String time = eventTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
        String classification = "[" + EVENT_SYMBOL + "]";


        return classification + super.toString() + " (at: " + date + " " + time + ")";
    }
}
