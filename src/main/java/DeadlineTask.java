/**
 * DeadlineTask which inherits from Task class.
 */
public class DeadlineTask extends Task {

    /** Deadline of task. */
    private String by;

    /**
     * Constructor for DeadlineTask class.
     *
     * @param desc Description of DeadlineTask.
     * @param by Deadline of DeadlineTask.
     */
    public DeadlineTask(String desc, String by) {
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
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
