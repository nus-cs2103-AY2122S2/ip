import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DeadlineTask which inherits from Task class.
 */
public class DeadlineTask extends Task {

    /** Deadline of task. */
    private LocalDateTime by;

    /**
     * Constructor for DeadlineTask class.
     *
     * @param desc Description of DeadlineTask.
     * @param by Deadline of DeadlineTask.
     */
    public DeadlineTask(String desc, LocalDateTime by) {
        super(desc);
        this.by = by;
    }

    /**
     * String representation of DeadlineTask.
     *
     * @return String representation of DeadlineTask.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, K:mma");
        return "[D]" + super.toString() + " (by: " + this.by.format(formatDateTime) + ")";
    }
}
