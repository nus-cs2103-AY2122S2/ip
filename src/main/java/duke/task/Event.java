package duke.task;

import java.time.LocalDateTime;

import duke.dukeexceptions.DukeDateExceptions;
import duke.dukeexceptions.DukeException;


/**
 * Events is a Type of Task, containing extra information.
 */
public class Event extends Task {

    private static final String TYPE = "E";
    private String info;
    private LocalDateTime dateInfo;

    /**
     * Constructor of Events
     * @param name name of the event.
     * @param dateInfo extra dateInfo of the event.
     */
    public Event(String name, String dateInfo) throws DukeException {
        super(name);
        this.info = dateInfo;
        try {
            this.dateInfo = LocalDateTime.parse(dateInfo, Task.DATE_TIME_FORMAT_IN);
        } catch (Exception e) {
            throw new DukeDateExceptions("");
        }
    }

    /**
     * Constructor for the event class
     * @param name name of the event.
     * @param marked is event marked.
     * @param dateInfo string representation of the date.
     */
    public Event(String name, boolean marked, String dateInfo) {
        super(name);
        this.dateInfo = this.dateInfo = LocalDateTime.parse(dateInfo, Task.DATE_TIME_FORMAT_IN);;
        this.isMarked = marked;
    }

    @Override
    public String toStore() {
        return TYPE + " | " + this.markStore() + " | " + this.name + " | " + this.info;
    }

    @Override
    public String display() {
        return "[" + TYPE + "] " + "[" + markDisplay() + "] "
                + this.name + " (at " + dateInfo.format(Task.DATE_TIME_FORMAT_OUT) + ")";
    }

}
