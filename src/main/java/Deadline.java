public class Deadline extends Task {
    protected String dateAndTime;

    public Deadline(String description, int taskNumber, String dateAndTime) {
        super(description, taskNumber);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return super.taskNumber + "." + "[D]" + this.getStatusIcon() + " " + super.description + "(by:" + this.dateAndTime + ")";
    }
}
