public class Deadline extends Task {
    protected String by;

    Deadline(String description, int id, String by) {
        super(description, id);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
