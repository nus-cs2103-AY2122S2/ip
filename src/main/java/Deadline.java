/**
 * The Deadline class extends the Task class by adding a due time.
 *
 * @author Rdac0
 */
public class Deadline extends Task{
    private String time;

    /**
     * Creates a Deadline object.
     *
     * @param name The name of the Deadline.
     * @param time The due time of the Deadline.
     */
    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        String mark;
        if (super.getDone()) {
            mark = "[X] ";
        } else {
            mark = "[ ] ";
        }
        return "[D]" + mark + getName() +
                " (by: " + this.time + ")";
    }
}
