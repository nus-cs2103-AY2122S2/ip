public class Deadline extends Task{
    protected String by;
    public Deadline(String task, String by) {
        super(task.trim());
        this.by = by.trim();
    }

    public Deadline(String task, boolean done) {
        super(task, done);
    }

    @Override
    public Deadline mark() {
        return new Deadline(task, true);
    }

    @Override
    public Deadline unmark() {
        return new Deadline(task, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
