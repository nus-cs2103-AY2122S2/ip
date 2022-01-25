public class Deadline extends Task {
    protected String by;

    Deadline(String taskName, String date) {
        super(taskName);
        this.by = date;
    }

    public String toSaveString() {
        return "D@@" + (this.isDone() ? "1@@" : "0@@")
                + this.name + "@@" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.by);
    }
}
