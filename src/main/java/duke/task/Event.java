package duke.task;

import java.time.LocalDateTime;

import duke.util.DateTimeCustomFormatter;

/**
 * Represents an Event object
 */
public class Event extends Task {
    private LocalDateTime metaInfo;

    /**
     * Default constructor
     * 
     * @param description description of the task
     * @param metaInfo    duration information of the task
     */
    public Event(String description, String metaInfo) {
        super(description, "E");
        this.metaInfo = DateTimeCustomFormatter.getDateFromString(metaInfo);
    }

    /**
     * This constructor initializes event objects with isDone specified
     * 
     * @param isDone      whether this task is done or not
     * @param description description of the task
     * @param metaInfo    duration information of the task
     */
    public Event(boolean isDone, String description, String metaInfo) {
        super(description, "E");
        if (isDone) {
            setDone();
        } else {
            setUndone();
        }
        this.metaInfo = DateTimeCustomFormatter.getDateFromString(metaInfo);
    }

    /**
     * @return String default string representation of an event
     */
    @Override
    public String toString() {
        return super.toString() + "(by:" + DateTimeCustomFormatter.getStringFromDate(this.metaInfo) + ")";

    }

    /**
     * @return String string representation of this task to be saved to file
     */
    @Override
    public String getPrintString() {
        return super.getPrintString() + "|" + DateTimeCustomFormatter.getStringFromDate(this.metaInfo);
    }
}