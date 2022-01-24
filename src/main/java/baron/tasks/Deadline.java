package baron.tasks;

public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.strip();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toSaveString(String delimiter) {
        return "D" + delimiter + super.toSaveString(delimiter) + delimiter + this.by;
    }
}
