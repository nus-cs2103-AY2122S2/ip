package stevie.task;

import java.util.Date;

/**
 * A type of task that can be characterised as an event. An <code>stevie.task.EventTask</code> contains
 * a name and an event date.
 */
public class EventTask extends Task {
    private Date date;

    /**
     * Constructor for an event task.
     *
     * @param name name of the event
     * @param date date of the event
     */
    public EventTask(String name, Date date) {
        super(name);
        this.date = date;
    }

    /**
     * Generates a formatted string to be written to a .txt save file.
     *
     * @return a formatted string that can be read to restore the task
     */
    @Override
    public String generateTaskSaveData() {
        return "E|" + (isDone ? "1" : "0") + "|" + name + "|" + formatter.format(date);
    }

    /**
     * Generates a formatted string to display the state and type of task.
     *
     * @return a formatted string to be displayed for user
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatter.format(date) + ")";
    }
}
