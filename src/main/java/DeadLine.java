public class DeadLine extends Task {
    protected String by;

    public DeadLine(String objective, String by) {
        super(objective);
        this.by = by;
    }
    public DeadLine(String objective, Boolean done, String by) {
        super(objective, done);
        this.by = by;
    }
    @Override
    public String serialize() {
        return "D|" + (this.done ? "1|" : "0|") + this.objective + "|" + this.by +"\n";
    };

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}