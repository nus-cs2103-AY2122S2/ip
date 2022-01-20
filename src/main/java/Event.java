public class Event extends Task {
    protected String dateAndTime;

    public Event(String description, int taskNumber, String dateAndTime) {
        super(description, taskNumber);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return super.taskNumber + "." + "[E]" + this.getStatusIcon() + " " + super.description + "(at:" + this.dateAndTime + ")";
    }
}
