package task;

public class Deadline extends Task{
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description, "D");
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", this.getDeadline());
    }
}
