public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description, false);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String formatData() {
        return "D|" + super.formatData() + "|" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
