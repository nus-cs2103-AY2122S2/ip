package chatbot.task;

import chatbot.datetime.Timestamp;


/**
 * Represents a Task with a Timestamp at which it will occur.
 */
public class Event extends Task {

    private final Timestamp timestamp;

    /**
     * Instantiates a new Event.
     *
     * @param title The title of the event.
     * @param timestamp  The timestamp of the event.
     */
    public Event(String title, Timestamp timestamp) {
        super(title, "E", timestamp);
        this.timestamp = timestamp;
    }

    /**
     * Instantiates a new Event.
     *
     * @param title The title of the event.
     * @param done  The completion status of the event.
     * @param timestamp  The timestamp of the event.
     */
    public Event(String title, String done, Timestamp timestamp) {
        super(title, "E", done, timestamp);
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), timestamp);
    }
}
