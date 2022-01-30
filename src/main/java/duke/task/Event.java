package duke.task;

import duke.file_management.Time;

/**
 * One of the three tasks that a user can indicate.
 * Indicates an Event that the user has.
 *
 * @author Justin Ng Jie Ern
 */
public class Event extends Task{
    /**
     * Time Object that is the deadline for the Task.
     */
    private Time time;

    /**
     * Constructor for Event.
     *
     * @param name Name of the Event.
     * @param isChecked Boolean on whether the Event is marked or not.
     * @param taskLabel "E" to represent a Event task.
     * @param eventDate String of date and time of the Event.
     */
    public Event(String name, boolean isChecked, String taskLabel, String eventDate) {
        super(name, isChecked, taskLabel);
        Time time = new Time(eventDate);
        this.time = time;
    }

    /**
     * Prints out information of Event.
     *
     * @return String of Event Task.
     */
    @Override
    public String toString() {
        return "[" + super.getTaskLabel() + "][" + super.isTaskCheck() + "] " + super.toString() +
                " (at: " + this.time + ")";
    }
}