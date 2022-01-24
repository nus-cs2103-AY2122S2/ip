public class Deadline extends Task {
    private String by;

    public Deadline(String description, String time) {
        super(description, Type.DEADLINE);
        this.by = time;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
