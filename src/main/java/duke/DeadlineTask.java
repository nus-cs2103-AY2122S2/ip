package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents task of deadline type.
 *
 * @author sibinhho99-nus
 */
public class DeadlineTask extends Task {
    private LocalDate doneBy;

    /**
     * Constructor for task of deadline type.
     * @param isDone whether the task is done.
     * @param name the name of the task.
     * @param doneBy the deadline of the task.
     */
    public DeadlineTask(boolean isDone, String name, String doneBy) throws DateTimeParseException {
        super(isDone, name);
        this.doneBy = LocalDate.parse(doneBy);
    }

    /**
     * Returns deadline of current task.
     * @return deadline of the current task.
     */
    public LocalDate getDoneBy() {
        return this.doneBy;
    }

    /**
     * Returns String representation of the Task.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.isDone() ? "X" : " ", this.getName(), doneBy.toString());
    }
}
