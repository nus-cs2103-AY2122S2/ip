public class Deadline extends Task {
    protected String by;
    public Deadline(String title,String by) {
        super(title);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
