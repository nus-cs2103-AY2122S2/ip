public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String getType() {
        return  "D";
    }

    @Override
    public String formatToSave() {
        return super.formatToSave() + "|" + this.by;
    };

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}