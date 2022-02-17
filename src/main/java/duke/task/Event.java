package duke.task;

import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.dukeexceptions.DukeDateExceptions;
import duke.dukeexceptions.DukeException;



/**
 * Events is a Type of Task, containing extra information.
 */
public class Event extends Task {

    private static final String TYPE = "E";
    private String info;
    private LocalDateTime dateInfo;
    private LocalTime endTime;

    /**
     * Constructor of Events
     * @param name name of the event.
     * @param info extra dateInfo of the event.
     */
    public Event(String name, String info) throws DukeException {
        super(name);
        this.info = info;
        try {
            String[] times = info.split("-");
            this.endTime = LocalTime.parse(times[1], Task.TIME_FORMAT_IN);
            this.dateInfo = LocalDateTime.parse(times[0], Task.DATE_TIME_FORMAT_IN);
        } catch (Exception e) {
            throw new DukeDateExceptions("");
        }
    }

    /**
     * Constructor for the event class
     * @param name name of the event.
     * @param marked is event marked.
     * @param info string representation of the date.
     */
    public Event(String name, boolean marked, String info) {
        super(name);
        this.info = info;
        String[] times = info.split("-");
        this.endTime = LocalTime.parse(times[1], Task.TIME_FORMAT_IN);
        this.dateInfo = LocalDateTime.parse(times[0], Task.DATE_TIME_FORMAT_IN);
    }

    @Override
    public String toStore() {
        return TYPE + " | " + this.markStore() + " | " + this.name + " | " + this.info;
    }

    @Override
    public String display() {
        return "[" + TYPE + "] " + "[" + markDisplay() + "] "
                + this.name + " (at " + dateInfo.format(Task.DATE_TIME_FORMAT_OUT) + " - "
                + endTime.format(Task.TIME_FORMAT_OUT) + ")";
    }

}
