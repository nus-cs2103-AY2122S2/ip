package Duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;

/**
 * a subclass of task
 */
public class Deadline extends Task implements Serializable {
    private LocalDate by;

    /**
     * Constructor
     * @param description task description
     * @param by date to the task to be complete
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * get the date of this task
     * @return LocalDate
     */
    public LocalDate getDate() {
        return this.by;
    }

    /**
     * String representation
     * @return String representation of this task
     */
    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription()
                + "by (" + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}