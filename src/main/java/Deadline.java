public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Returns the save format in String of this Task object
     * @return A String for the save format of this Task object
     */
    @Override
    public String getSaveFormat() {
        return "D," + ((isDone ? "1" : "0")) + "," + super.getSaveFormat() + "," + this.by;
    }
}
