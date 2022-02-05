package alfred.task;

import java.time.LocalDateTime;

/**
 * Encapsulates the event task.
 */
public class Event extends Task {
    protected final static String type = "E";
    private final LocalDateTime dateTime;
    private final String dateAndTime;

    /**
     * Constructs an Event object.
     *
     * @param description String name of the event.
     * @param dateAndTime Date and time of the event in ISO format.
     */
    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
        this.dateTime = LocalDateTime.parse(dateAndTime);
    }

    protected Event(Boolean marked, String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
        this.dateTime = LocalDateTime.parse(dateAndTime);
        if (marked) {
            this.markComplete();
        }
    }

    @Override
    public String taskToSaveString() {
        String mark = this.isCompleted ? Task.COMPLETION_MARK : Task.INCOMPLETE_MARK;
        return String.join(Task.FORMAT_SPLIT, this.type, mark, this.description, this.dateAndTime);
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (at: "
                + super.localDateTimeToString(this.dateTime) + ")";
    }
}