public class Deadline extends Task {
    protected String by;
    protected String desc;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toMemory() {
        return "D" + super.toMemory() + "@" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
