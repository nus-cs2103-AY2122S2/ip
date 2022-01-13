package Tasks;

public class Event extends Task {
    String dateTime; // (at: date time)
    public Event(String task, boolean markStatus, String dateTime) {
        super(task, markStatus);
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return "(at:" + this.dateTime + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + this.getDateTime();
    }
}
