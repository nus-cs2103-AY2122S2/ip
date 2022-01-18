public class Deadline extends Task {
    private static final String tag = "D";
    private String date;

    public Deadline(String name, String deadline) {
        this(name, deadline, false);
    }

    public Deadline(String name, String deadline, Boolean done) {
        super(name, done);
        this.date = deadline;
    }

    @Override
    public String nameWithStatus() {
        return String.format("[%c][%c] %s (by: %s)",
                this.tag,
                super.isDone(),
                super.getName(),
                this.date);
    }
}
