package chatbot.task;

import chatbot.datetime.Timestamp;


/**
 * Represents a Task with a Timestamp by which it must be completed.
 */
public class Deadline extends Task {

    private final Timestamp by;

    /**
     * Instantiates a new Deadline.
     *
     * @param title The title of the deadline.
     * @param by The timestamp of the deadline.
     */
    public Deadline(String title, Timestamp by) {
        super(title, "D", by);
        this.by = by;
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param title The title of the deadline.
     * @param done The completion status of the deadline.
     * @param by The timestamp of the deadline.
     */
    public Deadline(String title, String done, Timestamp by) {
        super(title, "D", done, by);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}
