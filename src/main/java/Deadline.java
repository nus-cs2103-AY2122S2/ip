public class Deadline extends Task {

    private String deadline;

    public Deadline(boolean completed, String task, String deadline) {
        super(task, completed);
        this.deadline = deadline;
    }

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
