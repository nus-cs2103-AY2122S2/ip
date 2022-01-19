public class Deadline extends Task {

    protected String by;
    protected String type;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = "deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}