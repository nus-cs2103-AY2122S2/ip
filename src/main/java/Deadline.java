public class Deadline extends Task{

    protected String by;

    public Deadline(boolean status, String description, String by) {
        super(description);
        this.by = by;
        super.isDone = status;
    }

    @Override
    public String appendtoFile() {
        return "D|" + (super.isDone ? "1" : "0") + "|" + super.description + "|" + this.by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
