public class Deadline extends Task {
    String by;

    public Deadline(String activity, String by) {
        super(activity, "D");
        this.by = by;
    }
    @Override
    public String getStatus() {
        if (this.status == 0) {
            return "[" + type + "][ ] " + activity + " (by " + by + ")";
        } else {
            return "[" + type + "][X] " + activity + " (by " + by + ")";
        }
    }

    @Override
    public String toString() {
        return type + "|" + status + "|" + activity + "|" + by + "|\n";
    }
}
