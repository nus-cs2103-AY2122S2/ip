public class Event extends Task {
    protected String at;

    /**
     * Constructs a Event class for tasks that starts and ends at specific time
     * @param description String
     * @param id int, the task number
     * @param at String, the timeframe of the Event
     */
    Event(String description, int id, String at) {
        super(description, id);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
