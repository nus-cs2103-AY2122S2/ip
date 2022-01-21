public class Deadline extends Task {
    private final String deadline;
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public Deadline(String taskName, boolean isDone, String deadline) {
        super(taskName, isDone);
        this.deadline = deadline;

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.deadline);
    }

    @Override
    public String encode() {
        return "D <> " + super.encode() + " <> " + deadline + "\n";
    }
}
