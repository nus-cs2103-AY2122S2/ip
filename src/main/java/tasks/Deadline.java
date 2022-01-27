package tasks;

public class Deadline extends Task {

    protected String deadlineBy;

    public Deadline(String description, String by) {
        super(description);
        this.deadlineBy = by;
    }

    public String getDeadlineBy() {
        return deadlineBy;
    }

    public void setDeadlineBy(String deadlineBy) {
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadlineBy + ")";
    }
}
