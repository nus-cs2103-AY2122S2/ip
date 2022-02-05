package juke.task;

import juke.common.DateTimeHandler;
import juke.exception.JukeParseException;

/**
 * Task with a starting time.
 */
public class Event extends Task {
    /**
     * Starting time.
     */
    private DateTimeHandler date;
    
    /**
     * Constructor to initialize task with a description and a starting time.
     *
     * @param description
     * @param time
     */
    public Event(String description, String time) throws JukeParseException {
        super(description);
        this.date = new DateTimeHandler(time);
    }
    
    /**
     * Get the starting time.
     *
     * @return Start time.
     */
    public String getTime() {
        return this.date.getDateTime();
    }
    
    /**
     * Returns the task icon.
     *
     * @return Task icon.
     */
    @Override
    String getTaskIcon() {
        return TaskType.EVENT.getTaskIcon();
    }
    
    /**
     * Returns info about the task including its starting time.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getTime() + ")";
    }
}
