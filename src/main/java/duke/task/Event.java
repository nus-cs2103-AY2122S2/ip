package duke.task;

import java.util.StringTokenizer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event are tasks that start at a specific time and ends at a specific time.
 *
 * <p>e.g., team project meeting on 2/10/2019 2-4pm.</>
 */
public class Event extends Task {
    private LocalDate eventDate;
    private LocalTime eventTime;
    private static final char EVENT_SYMBOL = 'E';

    /**
     * Default constructor for Event.
     *
     * <p>Calls super class, Task, default constructor.
     * Sets eventDate and eventTime to default values 2020-12-12 and 2359.</>
     */
    public Event() {
        super();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        this.eventDate = LocalDate.parse("2020-12-12"); // just get default date
        this.eventTime = LocalTime.parse("2359", timeFormatter);
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
        return EVENT_SYMBOL + "|" + this.isDone + "|" + taskDescription + "|"
                + this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + "|"
                + this.eventTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + "\n";
    }

    /**
     * Extracts and initializes data to deadline from a string.
     *
     * @param data The string to extract data and initialize data from.
     *             Data format: E|true/false|taskDescription|MMM d yyyy|hh:mm a
     */
    @Override
    public void extractFileData(String data) {
        StringTokenizer st = new StringTokenizer(data, "|");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        st.nextToken(); // remove the type symbol
        isDone = Boolean.parseBoolean(st.nextToken());
        taskDescription = st.nextToken();
        eventDate = LocalDate.parse(st.nextToken(), dateFormatter);
        eventTime = LocalTime.parse(st.nextToken(), timeFormatter);
    }

    /**
     * Gets the Event information in string format.
     *
     * @return Event information in the following string format:
     * [E][ ] taskDescription (at: date time)
     */
    @Override
    public String toString() {
        return "[" + EVENT_SYMBOL + "]" + super.toString() + " (at: "
                + this.eventDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " "
                + this.eventTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
                + ")";
    }
}
