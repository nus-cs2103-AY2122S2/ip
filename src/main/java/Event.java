/**
 * Encapsulate information of event task.
 */
public class Event extends Task {
    protected String at;

    /**
     * Normal constructor.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the representative string for saving in data file.
     */
    @Override
    public String toFileFormat() {
        int status = super.isDone ? 1 : 0;
        return "E" + " | " + status + " | " + super.description + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
