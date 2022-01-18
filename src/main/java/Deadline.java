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
    public String getFullDetails() {
        return (super.getFullDetails() + "(by: " + this.deadlineDate + ")");
    }
}
