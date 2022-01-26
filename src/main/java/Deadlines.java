/**
 * Deadlines tasks that need to be done before a specific date/time
 * e.g., submit report by 11/10/2019 5pm
 */

public class Deadlines extends Task {

    private final String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline + ")";
    }
}
