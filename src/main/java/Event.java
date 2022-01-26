public class Event extends Task {
    private final String at;

    /**
     * Public constructor for the Event object.
     * @param description the task name
     * @param at the duration in which the event takes place
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%1$s (at: %2$s)", super.toString(), this.at);
    }

    @Override
    public String toOutputFormat() {
        return String.format("E / %1$s / %2$s", super.toOutputFormat(), this.at);
    }
}
