public class Deadline extends Task {
    private final String time;

    public Deadline(String content, String time) {
        super(content);
        this.time = time;
    }

    public Deadline(String content, String time, boolean isDone) {
        super(content, isDone);
        this.time = time;
    }

    @Override
    public Deadline mark(boolean isDone) {
        return new Deadline(super.getContent(), time, isDone);
    }

    @Override
    public String fileFormat() {
        return "D" + super.fileFormat() + " | " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
