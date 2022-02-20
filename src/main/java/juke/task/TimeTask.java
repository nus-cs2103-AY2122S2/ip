package juke.task;

import juke.common.DateTimeHandler;
import juke.exception.JukeParseException;

/**
 * Abstraction for a task containing a time element.
 */
public abstract class TimeTask extends Task {
    /**
     * Starting time.
     */
    protected DateTimeHandler date;

    /**
     * Constructor to initialize a task with a description.
     *
     * @param description Description.
     * @param type Task type
     * @param time Time as a string.
     * @throws JukeParseException Throws if parse error.
     */
    public TimeTask(String description, TaskType type, String time) throws JukeParseException {
        super(description, type);
        setTime(time);
    }
    /**
     * Constructor with no time for cloning.
     *
     * @param description Description.
     * @param type Task type
     * @throws JukeParseException Throws if parse error.
     */
    protected TimeTask(String description, TaskType type) {
        super(description, type);
        date = new DateTimeHandler();
    }

    /**
     * Gets the time.
     *
     * @return Time.
     */
    public String getTime() {
        assert date.getDateTime() != null;
        return date.getDateTime();
    }

    /**
     * Sets the time.
     *
     * @param time Time.
     * @throws JukeParseException Throws if parse error.
     */
    public void setTime(String time) throws JukeParseException {
        date = new DateTimeHandler(time);
    }
}
