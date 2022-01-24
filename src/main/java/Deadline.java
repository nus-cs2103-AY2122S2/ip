public class Deadline extends Task{
    protected String by;

    public Deadline(String deadlineName, String by) {
        super(deadlineName);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

    public String write() {
        String markStatus = super.isMarked ? "1" : "0";
        return String.format("%s | %s | %s | %s\n", "D", markStatus, super.taskName, this.by);
    }
}
