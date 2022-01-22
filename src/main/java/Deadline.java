public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    @Override
    public String getTime() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
