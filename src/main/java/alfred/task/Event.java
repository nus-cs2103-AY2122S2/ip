package alfred.task;

import java.time.LocalDateTime;

/**
 * Encapsulates the event task.
 */
public class Event extends Task {
    protected static final String type = "E";
    private final LocalDateTime dateTime;
    private final String dateAndTime;
    private final String description;

    /**
     * Constructs an Event object.
     *
     * @param description String name of the event.
     * @param dateAndTime Date and time of the event in ISO format.
     */
    public Event(String description, String dateAndTime) {
        super(description);
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.dateTime = LocalDateTime.parse(dateAndTime);
    }

    protected Event(Boolean marked, String description, String dateAndTime) {
        super(description);
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.dateTime = LocalDateTime.parse(dateAndTime);
        if (marked) {
            this.markComplete();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String taskToSaveString() {
        String mark = this.isCompleted ? Task.COMPLETION_MARK : Task.INCOMPLETE_MARK;
        return String.join(Task.FORMAT_SPLIT, Event.type, mark, this.description, this.dateAndTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + Event.type + "]" + super.toString() + " (at: "
                + Task.localDateTimeToString(this.dateTime) + ")";
    }

    @Override
    public boolean equals(Task task) {

        if (!(task instanceof Event)) {
            return false;
        }

        Event eventTask = (Event) task;
        return this.description.equals(eventTask.description)
                && this.dateTime.equals(eventTask.dateTime);
    }
}