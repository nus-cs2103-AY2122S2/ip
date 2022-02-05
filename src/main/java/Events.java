/**
 * tasks that STARTS at a specific time and ENDS at a specific time.
 */
public class Events extends Task {

    public String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.getDescription() + " (at: " + at + ")";
    }
}
