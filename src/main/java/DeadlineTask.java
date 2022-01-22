/**
 * A type of task that can be characterised as an deadline. A <code>DeadlineTask</code> contains
 * a name and an deadline.
 */
public class DeadlineTask extends Task {
    /**
     * Deadline of the task
     */
    private String date;

    /**
     * Constructor for a deadline task
     *
     * @param name name of the task
     * @param date deadline of the task
     */
    public DeadlineTask(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
