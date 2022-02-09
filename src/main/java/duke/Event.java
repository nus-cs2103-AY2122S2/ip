package duke;

/**
 * Represents a task within a duration of time.
 */
public class Event extends Task {
    private final String time;

    /**
     * Class constructor.
     * The task generated is by default not done.
     *
     * @param content description of the task.
     * @param time duration of the task.
     */
    public Event(String content, String time) {
        super(content);
        this.time = time;
    }

    /**
     * Class Constructor.
     *
     * @param content description of the task.
     * @param time duration of the task.
     * @param isDone whether the task is done.
     */
    public Event(String content, String time, boolean isDone) {
        super(content, isDone);
        this.time = time;
    }

    @Override
    public Event mark(boolean isDone) {
        return new Event(super.getContent(), time, isDone);
    }

    @Override
    public String fileFormat() {
        return "E" + super.fileFormat() + time + " | ";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
