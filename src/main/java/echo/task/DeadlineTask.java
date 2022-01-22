package echo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DeadlineTask which inherits from Task class.
 */
public class DeadlineTask extends Task {

    /** Deadline of task. */
    private final LocalDateTime BY;

    /**
     * Constructor for DeadlineTask class.
     *
     * @param desc Description of DeadlineTask.
     * @param by Deadline of DeadlineTask.
     */
    public DeadlineTask(String desc, LocalDateTime by) {
        super(desc);
        this.BY = by;
    }

    /**
     * String representation of DeadlineTask for saving.
     *
     * @return String representation of DeadlineTask for saving.
     */
    @Override
    public String saveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
        return "D | " + super.saveFormat() + " | " + this.BY.format(format);
    }

    /**
     * String representation of DeadlineTask.
     *
     * @return String representation of DeadlineTask.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + this.BY.format(formatDateTime) + ")";
    }
}
