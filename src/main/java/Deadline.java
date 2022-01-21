public class Deadline extends Task {

    protected String icon = "D";
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getDescription() {
        return description + " /by " + by;
    }

    @Override
    public Deadline mark() { return new Deadline(description, true, by); }

    @Override
    public Deadline unmark() { return new Deadline(description, false, by); }

}
