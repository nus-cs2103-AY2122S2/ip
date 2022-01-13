package Tasks;

public class Deadline extends Task {
    String deadline; // (at: date time)
    public Deadline(String task, boolean markStatus, String deadline) {
        super(task, markStatus);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return "(by:" + this.deadline + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.getDeadline();
    }
}
