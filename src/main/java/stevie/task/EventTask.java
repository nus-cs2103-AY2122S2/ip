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

    @Override
    public String generateTaskSaveData() {
        return "E|" + (isDone ? "1" : "0") + "|" + name + "|" + formatter.format(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatter.format(date) + ")";
    }
}
