public class Deadline extends Task {
    protected String by;

    public Deadline(String desc, String by) {
        super(desc, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String toString() {
        return getType() + super.toString() + " (By: " + by + ")";
    }
}
