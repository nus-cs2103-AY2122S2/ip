package chatcat.tasks;

/**
 * The default Deadline class inherited from {@code Task}.
 *
 * @see Task
 */
public class Deadline extends Task {
    String deadline;
    String time;

    /**
     * Creates a {@code Deadline} object using a specified description.
     *
     * @param deadline the description of this task.
     * @param time the time of the task.
     */
    public Deadline(String deadline, String time) {
        super(deadline);
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Returns a representation in string of {@code Deadline} task.
     *
     * @return a representation in string of {@code Deadline} task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
}
