public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);

        if (by == null || by.isEmpty()) {
            throw new DukeException("The deadline of a Deadline cannot be empty.");
        }

        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
