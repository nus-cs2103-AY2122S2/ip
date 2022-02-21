package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A type of Task, which has a deadline the task is due by.
 */
public class Deadline extends Task {

    protected String by;
    private LocalDateTime dateTime;

    /**
     * Creates a Deadline Task.
     *
     * @param description description of Deadline Task.
     * @param by dateTime of Deadline Task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.dateTime = LocalDateTime.parse(this.by,
                //if fail to parse, throw exception
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * Creates a Deadline Task.
     *
     * @param description description of Deadline Task.
     * @param by dateTime of Deadline Task.
     * @param isDone status of Task, whether or not done.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
        this.dateTime = LocalDateTime.parse(this.by,
                //if fail to parse, throw exception
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * @return the DateTime in the new format: MMM dd yyyy HH:mm
     */
    public String reFormatDateTime() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Returns the task data for a task, for Storage to save and load.
     *
     * @return the Deadline Task Data in String for Storage to parse and load.
     */
    @Override
    public String getTaskData() {
        return super.getTaskData() + " | " + this.by;
    }

    /**
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.reFormatDateTime() + ")";
    }
}
