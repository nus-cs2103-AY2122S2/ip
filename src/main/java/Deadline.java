public class Deadline extends Task {
    private String deadline;
    private static final char DEADLINE_SYMBOL = 'D';

    public Deadline() {
        super();

        this.deadline = "";
    }

    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);

        this.deadline = deadline;
    }

    public Deadline(boolean isDone, String taskDescription, String deadline) {
        super(isDone, taskDescription);

        this.deadline = deadline;
    }

    @Override
    public String saveFileFormat() {
        return DEADLINE_SYMBOL + "|" + this.isDone + "|" + taskDescription + "|" + this.deadline + "\n";
    }

    @Override
    public String toString() {
        return "[" + DEADLINE_SYMBOL + "]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
