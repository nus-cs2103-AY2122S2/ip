package chatbot.task;

import chatbot.datetime.Timestamp;

public class Event extends Task {

    private final Timestamp time;

    public Event(String title, Timestamp time) {
        super(title, "E", time);
        this.time = time;
    }

    public Event(String title, String done, Timestamp time) {
        super(title, "E", done, time);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), time);
    }
}
