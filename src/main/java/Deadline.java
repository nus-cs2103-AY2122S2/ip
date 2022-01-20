public class Deadline extends Task {
    private final String by;

    Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + "(by: " + by + ")");
    }
}
