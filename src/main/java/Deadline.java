public class Deadline extends Task {

    protected String by;

    public Deadline(String description) {
        super(description);
    }

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone) {
        super(description, isDone);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getTaskData() {
        String isDone = this.getStatusIcon() == "X" ? "1" : "0";
        if (by != null) {
            return "T " + isDone + " " + this.description + "/by " + this.by + "\n";
        } else {
            return "T " + isDone + " " + this.description + "\n";
        }
    }

    @Override
    public String toString() {
        if (by != null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            return "[D]" + super.toString();
        }
    }
}
