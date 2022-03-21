package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.util.DateTimeCustomFormatter;

/**
 * Represents a Deadline object
 */
public class Deadline extends Task {
    public static final String TASK_NAME = "Deadline";

    private LocalDateTime metaInfo;

    /**
     * Default constructor
     *
     * @param description description of the task
     * @param metaInfo    deadline information of the task
     */
    public Deadline(String description, String metaInfo) throws DateTimeParseException {
        super(description, "D");
        this.metaInfo = DateTimeCustomFormatter.getDateFromString(metaInfo);
    }

    /**
     * This constructor initializes deadline objects with isDone specified
     *
     * @param isDone      whether this task is done or not
     * @param description description of the task
     * @param metaInfo    deadline information of the task
     */
    public Deadline(boolean isDone, String description, String metaInfo) throws DateTimeParseException {
        super(description, "D");
        if (isDone) {
            setDone();
        } else {
            setUndone();
        }
        this.metaInfo = DateTimeCustomFormatter.getDateFromString(metaInfo);
    }

    /**
     * @return String default string representation of a deadline
     */
    @Override
    public String toString() {
        return super.toString() + " (by:" + DateTimeCustomFormatter.getStringFromDate(this.metaInfo) + ")";
    }

    /**
     * @return String representation of this task to be saved to file
     */
    @Override
    public String getPrintString() {
        return super.getPrintString() + "|" + DateTimeCustomFormatter.getStringFromDate(this.metaInfo);
    }
}
