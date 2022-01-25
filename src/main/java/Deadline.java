public class Deadline extends Task {
    protected String time;

    public Deadline(String name, String time) {
        this(name, time, false);
    }

    public Deadline(String name, String time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }

    @Override
    protected String convertToFileFormat() {
        if (isDone) {
            return "D | 1 | " + name + " | " + time;
        }
        return "D | 0 | " + name + " | " + time;
    }
}
