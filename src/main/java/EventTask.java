import java.time.LocalDate;
import java.time.LocalTime;

public class EventTask extends Task{
    protected LocalDate eventDate;
    protected LocalTime eventTime;

    public EventTask(String title, String eventDate){
        super(title);
        this.type = TaskType.EVENT;
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = null;
    }

    public EventTask(String title, String eventDate, String eventTime){
        super(title);
        this.type = TaskType.EVENT;
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = LocalTime.parse(eventTime);
    }

    public EventTask(String title, Boolean isDone, String eventDate){
        super(title, isDone);
        this.type = TaskType.EVENT;
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = null;
    }

    public EventTask(String title, Boolean isDone, String eventDate, String eventTime){
        super(title, isDone);
        this.type = TaskType.EVENT;
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = LocalTime.parse(eventTime);
    }

    public String getEventTime(){
        return "(at: " + eventDate + (eventTime != null ? " " + eventTime.toString() : "") + ")";
    }

    @Override
    public String toString(){
        return this.title + " " + getEventTime();
    }
}
