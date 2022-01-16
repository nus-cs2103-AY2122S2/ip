//@@author junrong98-reused
//Reused from https://nus-cs2103-ay2122s2.github.io/website/admin/ip-w2.html

/**
 * The Deadline class which inherits from Task.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * The constructors for deadline class
     * @param description The description of task sent by the user
     * @param by The deadline which the task should be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}