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
     * @param description Description.
     * @param time Time.
     */
    public Event(String description, String time) throws JukeParseException {
        super(description, TaskType.EVENT);
        this.date = new DateTimeHandler(time);
        assert getTaskIcon() == TaskType.EVENT.getTaskIcon();
    }

    /**
     * Get the starting time.
     *
     * @return Start time.
     */
    public String getTime() {
        assert this.date.getDateTime() != null;
        return this.date.getDateTime();
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
