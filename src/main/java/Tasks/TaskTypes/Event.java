package Tasks.TaskTypes;

public class Event extends Task {
    protected String at;

    /**
     * Creates a new Event.
     *
     * @param title The title of the event.
     * @param at When the event is taking place.
     */
    public Event(String title, String at) {
        super(title);
        this.at = at;
    }

    /**
     * Generates a new Event nd initialises it with the
     * given completion status.
     *
     * @param isDone If the event is over.
     * @param title Description the title of the event.
     */
    public Event(boolean isDone, String title, String at) {
        super(isDone, title);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.at);
    }

    @Override
    public String encodeTask() {
        return String.format("T @@@ %b @@@ %s @@@ %s", super.isDone(), super.getTitle(), this.at);
    }
}
