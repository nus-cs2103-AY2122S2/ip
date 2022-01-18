public class Deadline extends Task {
    private String by;

    public Deadline(String description, String time) {
        super(description);
        this.by = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
