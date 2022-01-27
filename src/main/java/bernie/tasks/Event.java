package bernie.tasks;

public class Event extends Task {
    private String at;

    /**
     * Constructs an Event class for tasks that starts and ends at specific time
     * @param description String
     * @param at String, the timeframe of the Event
     */
    Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
