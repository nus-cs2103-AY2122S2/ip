public class Deadline extends Task {
    private final String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String isDone, String description, String by) {
        super(isDone, description);
        this.by = by;
    }

    @Override
    public String fileFormat() {
        return String.format("D | %s | %s\n", super.fileFormat(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}