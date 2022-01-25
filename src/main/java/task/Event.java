package task;

/**
 * The Event class is a type of Task which is used to represent an event that happens at a certain date/time.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of this event.
     *
     * @return the string representation of this event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    /**
     * Returns the save format of this task.
     *
     * @return A String representing the save format of this task.
     */
    @Override
    public String getSaveFormat() {
        return "E," + ((isDone ? "1" : "0")) + "," + super.getSaveFormat() + "," + this.at;
    }
}
