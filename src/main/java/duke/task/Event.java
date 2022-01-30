package duke.task;

/**
 * Represents an upcoming event associated with its time of occurrence. This event is a task that possesses a
 * state/status that is by default not done.
 */
public class Event extends Task {
    private final String type = "E";
    private final String time;
    private final String name;

    /**
     * Creates an Event that is initially undone.
     *
     * @param name Name of the Event.
     * @param time Time of the upcoming Event.
     */
    public Event(String name, String time) {
        super(name);
        this.name = name;
        this.time = time;
    }

    /**
     * Returns the String representation of a Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return this.name + "(at: " + this.time + ")";
    }

    /**
     * Returns the type of Task. In this case, it is an Event.
     *
     * @return Type of Task.
     */
    @Override
    public String track() {
        return "[" + this.type + "]";
    }

    /**
     * Returns the name of the Task.
     *
     * @return Name of Task.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Time of the event happening.
     *
     * @return Time of the event happening.
     */
    public String getTime() {
        return this.time;
    }
}
