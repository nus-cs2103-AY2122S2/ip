public class Deadline extends Task {
    protected String byTime;

    public Deadline(String description, boolean isMarked, String byTime) {
        super(description, isMarked);
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byTime + ")";
    }

    @Override
    public String toData() {
        return "D | " + (this.isMarked ? 1 : 0) + " | " + this.description + " | " + this.byTime;
    }
}
