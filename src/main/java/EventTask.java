import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task{
    private String dateStr;

    public EventTask(String description, boolean isCompleted, String dateStr){
        super(description, isCompleted);
        this.dateStr = dateStr;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString() + "(at: " + dateStr + ")";
    }
}
