package juke.task;

import juke.common.DateTimeHandler;
import juke.exception.JukeParseException;

/**
 * Task with a description and a deadline.
 */
public class Deadline extends TimeTask {
    /**
     * Constructor to initialize a task with a description and a deadline.
     *
     * @param description Description.
     * @param time Time.
     * @throws JukeParseException Throws if parse error.
     */
    public Deadline(String description, String time) throws JukeParseException {
        super(description, TaskType.DEADLINE, time);
        assert getTaskIcon() == TaskType.DEADLINE.getTaskIcon();
    }

    /**
     * Copy constructor for cloning.
     *
     * @param task Task to clone.
     * @throws CloneNotSupportedException Should not throw error.
     */
    private Deadline(Deadline task) throws CloneNotSupportedException {
        super(task.description, TaskType.DEADLINE);
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
     * Returns info about the task including the deadline.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + getTime() + ")";
    }

    /**
     * Returns a clone of this task.
     *
     * @return Clone of this task.
     * @throws CloneNotSupportedException Should not throw error.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Deadline(this);
    }
}
