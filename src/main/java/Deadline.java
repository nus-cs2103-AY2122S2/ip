public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    @Override
    public String toText() {
        return "D | " + (this.getDone() ? 1 : 0) + " | " + this.getName() + " | " + this.by + "\n";
    }
}
