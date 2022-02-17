package ann.data.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ann.data.InputPattern;

/**
 * Represents an event that happens at a specified time.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class Event extends Task {
    private static final TaskType TASK_TYPE = TaskType.EVENT;
    public static final String KEYWORD = TASK_TYPE.getKeyword();
    public static final String INPUT_FORMAT = "event [content] /at yyyy-MM-dd HH:mm";
    private String date;
    private String time;

    /**
     * Creates a new Event with the specified content and date and time.
     *
     * @param content a String which describes the event.
     * @param dateAndTime a String which describes dateAndTime the event occurs.
     */
    public Event(String content, String dateAndTime) {
        super(content);
        assert InputPattern.isValidDateTimeString(dateAndTime) : "An illegal date-time format "
                + "scenario is handled by the Parser";
        setDateAndTime(dateAndTime);
    }

    /**
     * Creates a new Event with the specified content and completion status.
     *
     * @param content a String which describes the event.
     * @param isDone a boolean which indicates whether the event is completed.
     */
    public Event(String content, boolean isDone) {
        super(content, isDone);
    }

    /**
     * Returns a newly created Event from the specified content, time and completion status.
     *
     * @param content a String which describes the event.
     * @param dateAndTime a String which describes the time (in the file format).
     * @param isDone a boolean which indicates whether the event is completed.
     * @return a newly created Event from the specified content, time and completion status.
     */
    public static Event createEventFromStorage(String content, String dateAndTime, boolean isDone) {
        assert !content.isBlank() : "All Events in storage should have a non-empty description";
        Event event = new Event(content, isDone);
        parseDateAndTime(event, dateAndTime);
        return event;
    }

    /**
     * Sets the date and time of the given Event to the result of parsing the given time
     * String (in file format).
     *
     * @param event
     * @param dateAndTime
     */
    private static void parseDateAndTime(Event event, String dateAndTime) {
        event.date = dateAndTime.substring(0, 11).trim();
        event.time = dateAndTime.substring(11).trim();
    }

    /**
     * Parses the time inputted by the user and sets the date and time to Strings in the file format.
     *
     * @param dateAndTime a String representing the time of the event (in user input format).
     */
    public void setDateAndTime(String dateAndTime) {
        String[] dt = dateAndTime.split(" ");
        LocalDate date = LocalDate.parse(dt[0]);
        LocalTime time = LocalTime.parse(dt[1]);
        this.date = date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        this.time = time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    /**
     * Returns the String representation of the Event to be displayed to users.
     *
     * @return the String representation of the Event to be displayed to users.
     */
    @Override
    public String toString() {
        return "[E]" + (super.isDone ? "[X] " : "[ ] ") + super.content + " (at " + this.date + " " + this.time + ")";
    }

    /**
     * Returns the String representation of the Event to be written to a file.
     *
     * @return the String representation of the Event to be written to a file.
     */
    @Override
    public String toFileString() {
        return "E, " + (super.isDone ? "1, " : "0, ") + super.content + ", " + this.date + " " + this.time;
    }
}
