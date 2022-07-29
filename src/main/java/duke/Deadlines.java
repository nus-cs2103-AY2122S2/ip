package duke;

/**
 * Deadlines tasks that need to be done before a specific date/time
 * e.g., submit report by 11/10/2019 5pm
 */

public class Deadlines extends Task {

    private final DateTime deadline;

    public Deadlines(String description, DateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Gets date and time of this Deadlines object
     *
     * @return date and time in String format
     */
    public String getDateTimeForStorage() {
        return this.deadline.dateTimeForStorage();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.toString() + ")";
    }
}
