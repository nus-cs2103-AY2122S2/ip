public class Deadline extends Task {
    private final String time;

    public Deadline(String content, String time) {
        super(content);
        this.time = time;
    }

    public Deadline(String content, String time, boolean done) {
        super(content, done);
        this.time = time;
    }

    @Override
    public Deadline mark(boolean done) {
        return new Deadline(super.getContent(), time, done);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + time + ")";
    }
}
