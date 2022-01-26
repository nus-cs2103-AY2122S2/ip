import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
    protected DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
    protected LocalDate eventDate;
    protected LocalTime eventTime;

    public Event(String taskName, LocalDate eventDate) {
        super(taskName);
        this.eventDate = eventDate;
    }
    
    public Event(String taskName, LocalDate eventDate, LocalTime eventTime) {
        super(taskName);
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }
    
    @Override
    public String toDataString() {
        String isDone = super.isMarked() ? "1" : "0";
        if (eventTime != null) {
            return getTaskIcon() + " | " + isDone + " | " + taskName 
                    + " | " + eventDate + " | " + eventTime;
        } else {
            return getTaskIcon() + " | " + isDone + " | " + taskName + " | " + eventDate;
        }
    }

    @Override
    public String toString() {
        if (eventTime != null) {
            return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " +
                    super.taskName + " (at: " + dateFormatter.format(eventDate)
                    + " " + timeFormatter.format(eventTime) + ")";
        } else {
            return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " +
                    super.taskName + " (at: " + dateFormatter.format(eventDate) + ")";
        }
    }
}
