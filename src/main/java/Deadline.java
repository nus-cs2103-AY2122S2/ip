public class Deadline extends Task {
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
        return String.format("[D]%s (by: %s)",
                super.nameWithStatus(),
                this.date);
    }
}
