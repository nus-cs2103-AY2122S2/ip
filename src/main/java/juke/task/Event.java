package juke.task;

import juke.common.DateTimeHandler;
import juke.exception.JukeParseException;

/**
 * Task with a starting time.
 */
public class Event extends TimeTask {
    /**
     * Constructor to initialize task with a description and a starting time.
     *
     * @param description Description.
     * @param time Time.
     * @throws JukeParseException Throws if parse error.
     */
    public Event(String description, String time) throws JukeParseException {
        super(description, TaskType.EVENT, time);
        assert getTaskIcon() == TaskType.EVENT.getTaskIcon();
    }

    /**
     * Copy constructor for cloning.
     *
     * @param task Task to clone.
     * @throws CloneNotSupportedException Should not throw error.
     */
    private Event(Event task) throws CloneNotSupportedException {
        super(task.description, TaskType.EVENT);
        try {
            this.date = new DateTimeHandler(task.date.getDateTime());
        } catch (JukeParseException e) {
            // Should not reach here
            assert false;
            throw new CloneNotSupportedException();
        }
        this.status = task.status;
        assert this != task;
    }

    /**
     * Returns info about the task including its starting time.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return super.toString() + " (at: " + getTime() + ")";
    }

    /**
     * Returns a clone of this task.
     *
     * @return Clone of this task.
     * @throws CloneNotSupportedException Should not throw error.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Event(this);
    }
}
