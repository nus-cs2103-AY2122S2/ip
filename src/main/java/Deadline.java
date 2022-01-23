public class Deadline extends Task {

    protected String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    String getTaskType() {
        return "D";
    }

    public String toString() {
        return String.format("[%s]", getTaskType())
                + super.toString()
                + String.format(" (by: %s)", deadline);
    }
}
