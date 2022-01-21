/**
 * The type Deadline.
 */
public class Deadline extends Task{
    /**
     * The due date for the given task.
     */
    protected String by;

    /**
     * Instantiates a new Deadline.
     *
     * @param by the input that has been delimited by " /by "
     */
    public Deadline(String deadlineName, String by) {
        super(deadlineName);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
