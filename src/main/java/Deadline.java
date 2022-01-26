public class Deadline extends Task {
    protected String by;
    char type;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.type = 'd';
    }

    public String toString() {
        return "[D]" + super.toString() + "(" + by + ")";
    }
}
