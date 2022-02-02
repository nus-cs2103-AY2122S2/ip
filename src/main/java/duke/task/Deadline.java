package duke.task;

import java.time.LocalDate;

/**
 * Represents a task which is of type deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for this class.
     * @param description Task description.
     * @param by Deadline due date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Task description that is formatted to be written into the file.
     *
     * @return Task description format for file input.
     */
    @Override
    public String taskDescriptionForFile() {
        return "D , 0 , "
                + this.description.trim() + " , " + this.by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(super.getOutputDateFormat()) + ")";
    }
}
