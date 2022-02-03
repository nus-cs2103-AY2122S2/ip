package juke.task;

import juke.DateTimeHandler;

/**
 * Task with a description and a deadline.
 */
public class Deadline extends Task {
    /**
     * Deadline.
     */
    private DateTimeHandler date;
    
    /**
     * Constructor to initialize a task with a description and a deadline.
     *
     * @param description
     * @param time
     */
    public Deadline(String description, String time) {
        super(description);
        this.date = new DateTimeHandler(time);
    }
    
    /**
     * Returns the deadline.
     *
     * @return Deadline.
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
    public String getTaskIcon() {
        return TaskType.DEADLINE.getTaskIcon();
    }
    
    /**
     * Returns info about the task including the deadline.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getTime() + ")";
    }
}
