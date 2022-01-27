/**
 * Represent tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected String time;

    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the object, including the task type(i.e. Deadline), description,
     * and time.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }

    /**
     * Provides the string representation of the task for data storage
     */
    @Override
    public String toStoreInfo() {
        String status = isDone ? "1" : "0";
        return "D | " + status + " | " + this.description + " | " + this.time + "\n";
    }
}
