public class DeadLine extends Task {
    protected String by;

    public DeadLine(String objective, String by) {
        super(objective);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}