
/**
 * Tasks that need to be done before a specific time/date.
 */
public class Deadlines extends Task {

    private String by;

    /**
     * Deadline will take in a description and a by timeline.
     */
    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (by: " + by + ")";
    }

}
