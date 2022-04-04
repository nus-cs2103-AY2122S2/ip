package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for Event, which extends the Task class
 *
 * @author Benjamin Koh
 */

public class Event extends Task {
    protected LocalDateTime time;


    /**
     * Constructor for a new instance of Event, which entails the name of the Event, and the Date & Time of the Event
     *
     * @param taskName Name of the event
     * @param time Date & Time of the event
     */


    public Event(String taskName, String time) {
        super(taskName);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
        this.time = LocalDateTime.parse(time, format);
    }

    /**
     * Return a string with the Task type, the isDone status, the Event name, and Date & Time of event
     *
     * @return a string with the Task type, the isDone status, the Event name, and Date & Time of event
     */

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        return "[E]" + super.toString() + " (at: " + time.format(displayFormat) + ")";
    }
}
