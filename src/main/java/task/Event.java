package task;

/**
 * The Event class is a type of Task which is used to represent an event that happens at a certain date/time.
 */
public class Event extends Task implements Comparable<Task> {
    protected String at;

    /**
     * Constructs a Event object.
     *
     * @param description the description of this deadline task.
     * @param at the date of this event task.
     */
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
        return "E," + ((isDone ? "1" : "0")) + "," + super.getSaveFormat() + "," + this.at + "\n";
    }

    /**
     * Compares 2 Tasks object and returns the order of the current Task
     * based on its type. If the other Task is an Event,
     * it compares based on its description in ascending order
     *
     * @param o The other Task object
     * @return The order
     */
    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline) {
            return 1;
        } else if (o instanceof Todo) {
            return -1;
        } else if (o instanceof Event) {
            return this.description.compareTo(o.description);
        }
        return 0;
    }
}
