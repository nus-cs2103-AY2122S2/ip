public class Deadline extends Task {
    private String deadline;
    private static final char DEADLINE_SYMBOL = 'T';

    public Deadline() {
        super();

        this.deadline = "";
    }

    public Deadline(String taskDescription, String eventTime) {
        super(taskDescription);

        this.deadline = eventTime;
    }

    @Override
    public String saveFileFormat() {
        return DEADLINE_SYMBOL + "|" + isDone + "|" + taskDescription + "\n";
    }

    @Override
    public void readFile() {

    }

    @Override
    public String toString() {
        return "[" + DEADLINE_SYMBOL + "]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
