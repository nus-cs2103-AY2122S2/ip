public class Deadline extends Task {
    private String date;
    private char tag = 'D';

    public Deadline(String name, String deadline) {
        this(name, deadline, false);
    }

    public Deadline(String name, String deadline, Boolean done) {
        super(name, done);
        this.date = deadline;
    }

    @Override
    public String nameWithStatus() {
        return String.format("%s (by: %s)",
                super.nameWithStatus(),
                this.date);
    }

    @Override
    public String fileSaveFormat() {
        return String.format("%s||%s)",
                super.fileSaveFormat(),
                this.date);
    }
}
