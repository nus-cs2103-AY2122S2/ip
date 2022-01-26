public class Deadline extends Task {
    protected String deadlineDate;

    public Deadline(String taskName, String date) {
        super(taskName);
        this.deadlineDate = date;
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toDataString() {
        String isDone = super.isMarked() ? "1" : "0";
        return getTaskIcon() + " | " + isDone + " | " + taskName + " | " + deadlineDate;
    }

    @Override
    public String toString() {
        return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " +
                super.taskName + " (by: " + this.deadlineDate + ")";
    }
}
