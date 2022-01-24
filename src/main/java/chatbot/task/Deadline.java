package chatbot.task;

import chatbot.datetime.Timestamp;

public class Deadline extends Task {

    private final Timestamp by;

    public Deadline(String title, Timestamp by) {
        super(title, "D", by);
        this.by = by;
    }

    public Deadline(String title, String done, Timestamp by) {
        super(title, "D", done, by);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}
