package duke.taskobjects;

import java.time.LocalDate;

/**
 * Representation of a Task with a deadline.
 */
public class Deadline extends TaskWithDate {
    /**
     * Creates a Deadline task.
     *
     * @param name Task name or description.
     * @param deadline String representation of date.
     */
    public Deadline(String name, LocalDate deadline) {
        super(name, deadline);
    }

    /**
     * Creates a Deadline task.
     * Alternative constructor for Deadline used for importing existing task.
     *
     * @param name Task name or description.
     * @param isDone Boolean which shows if task is marked as done or not.
     * @param deadline String representation of date.
     */
    public Deadline(String name, boolean isDone, String deadline) {
        super(name, isDone, deadline);
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + super.getFormattedDate() + ")";
    }

    @Override
    public String getCurrentStatus() {
        return "[D]" + super.getCurrentStatus();
    }

    @Override
    public Types getType() {
        return Types.DEADLINE;
    }
}
