public class Deadline extends Task {
    protected String by;
    public Deadline(String title,String by) {
        super(title);
        this.by = by;
    }

    public Deadline(String title, boolean done, String by) {
        super(title, done);
        this.by = by;
    }

    public String getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
