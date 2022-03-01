package alfred.task;

import java.time.LocalDateTime;

/**
 * Encapsulates a deadline task.
 */
public class Deadline extends Task {

    protected static final String TYPE = "D";
    private static final String SYMBOL = "\u26A0";
    private final LocalDateTime dateTime;
    private final String dateAndTime;
    private final String description;

    /**
     * Constructs a deadline object.
     *
     * @param description String name of the task.
     * @param dateAndTime String input of the date and time of the deadline,
     *                    in ISO format.
     */
    public Deadline(String description, String dateAndTime) {
        super(description);
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.dateTime = LocalDateTime.parse(dateAndTime);
    }


    protected Deadline(Boolean marked, String description, String dateAndTime) {
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
        String mark = this.isCompleted ? Task.FORMAT_COMPLETION_MARK : Task.FORMAT_INCOMPLETE_MARK;
        return String.join(Task.FORMAT_SPLIT, Deadline.TYPE, mark, this.description,
                this.dateAndTime);
    }

    @Override
    public String toString() {
        return " " + Deadline.SYMBOL + " " + super.toString() + " (by: "
                + Task.localDateTimeToString(this.dateTime) + ")";

    }

    @Override
    public boolean equals(Task task) {

        if (!(task instanceof Deadline)) {
            return false;
        }

        Deadline deadlineTask = (Deadline) task;
        return this.description.equals(deadlineTask.description)
                && this.dateTime.equals(deadlineTask.dateTime);
    }
}
