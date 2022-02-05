package duke;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task to be completed. Includes a description of the task and a boolean representing
 * whether it has been completed.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Task {
    protected static final DateTimeFormatter YEAR_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    protected static final DateTimeFormatter OUTPUT_YEAR_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected static final DateTimeFormatter OUTPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("hh:mm a");
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class
     *
     * @param d a string representing a description of the task
     */
    public Task(String d) {
        this.description = d;
        this.isDone = false;
    }

    /**
     * Marks the task as "Done"
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as "Undone"
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the task properties in the format of the task to be saved onto hard disk (to be Overridden)
     *
     * @return String representing the task toString in hard-disk format
     */
    public String toStringInFileFormat() {
        return null;
    }

    /**
     * Retrieves the status of the task
     *
     * @return If marked as "Done", returns "X", otherwise returns " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // X means task is done
    }

    /**
     * Returns ToString implementation of the Task class
     *
     * @return String representation of the status of the task along with its description
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
