package chatbot;

public class Deadline extends Task {

    protected String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public Deadline(String taskName, String by, boolean isDone) {
        super(taskName, isDone);
        this.by = by;
    }

    public String getDateTime() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}