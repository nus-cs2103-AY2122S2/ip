package alfred.task;

import java.time.LocalDateTime;

/**
 * Encapsulates a deadline task.
 */
public class Deadline extends Task {

    protected static final  String type = "D";
    private final LocalDateTime dateTime;
    private final String dateAndTime;

    /**
     * Constructs a deadline object.
     *
     * @param description String name of the task.
     * @param dateAndTime String input of the date and time of the deadline,
     *                    in ISO format.
     */
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
        this.dateTime = LocalDateTime.parse(dateAndTime);
    }


    protected Deadline(Boolean marked, String description, String dateAndTime) {
        super(description);
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
        return String.join(Task.FORMAT_SPLIT, Deadline.type, mark, this.description,
                this.dateAndTime);
    }

    @Override
    public String toString() {
        return "[" + Deadline.type + "]" + super.toString() + " (by: "
                + Task.localDateTimeToString(this.dateTime) + ")";

    }
}
