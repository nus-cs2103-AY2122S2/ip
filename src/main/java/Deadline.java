public class Deadline extends Task {
    private String deadline;

    public Deadline() {
        super();

        this.deadline = "";
    }

    public Deadline(String taskDescription, String eventTime) {
        super(taskDescription);

        this.deadline = eventTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
