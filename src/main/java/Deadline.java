public class Deadline extends Task {
    protected String by;

    public Deadline(String[] details) {
        this.description = details[0];
        this.by = details[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
