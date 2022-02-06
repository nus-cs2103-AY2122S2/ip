package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event. An Event is a type of task that must be done from a certain date and time to another
 * date and time.
 */
public class Event extends Task{
    protected LocalDate date;
    private DateTimeFormatter dateOutputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private DateTimeFormatter timeOutputFormatter = DateTimeFormatter.ofPattern("HHmm");
    private LocalTime timeFrom;
    private LocalTime timeTo;

    /**
     * Constructor for Event.
     *
     * @param name Name of Deadline.
     * @param date Date Event starts.
     * @param timeFrom Time Event starts.
     * @param timeTo Time Event ends.
     */
    public Event(String name, LocalDate date, LocalTime timeFrom, LocalTime timeTo) {
        super("E", name, date, timeFrom, timeTo);
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    /**
     * Overrides toString to display type of Task, whether or not it is completed, its name,
     * and by when to start and end Task.
     *
     * @return String containing above information
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(dateOutputFormatter) + " " +
                timeFrom.format(timeOutputFormatter) + "-" + timeTo.format(timeOutputFormatter) + ")";
    }
}
