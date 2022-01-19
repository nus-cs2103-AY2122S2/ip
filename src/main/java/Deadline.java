public class Deadline extends Task {
    private String by;
    public Deadline(String content, String by) {
        super(content);
        this.by = by;
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return "    [D][X] " + getContent() + " (by: " + by + ")";
        } else {
            return "    [D][ ] " + getContent() + " (by: " + by + ")";
        }
    }
}
