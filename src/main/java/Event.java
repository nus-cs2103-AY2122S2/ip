import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected DateHelper at;

    public Event(String description, DateHelper at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTask() {
        return "[E]" + super.getTask() + "(at: " + at.getDatetime() + ")";
    }
}