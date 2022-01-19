package tasks;

public class Event extends Task {
    protected String at;

    /**
     * Constructor for the deadline object.
     *
     * @param description
     * @param at the deadline for the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
