import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    protected DateHelper at;
    private String type;

    public Event(String description, DateHelper at) {
        super(description);
        this.at = at;
        this.type = "E";
    }

    @Override
    public String getTask() {
        return "[" + this.type + "]" + super.getTask() + "(at: " + at.getDatetime() + ")";
    }

    @Override
    public String getDescription() {
        return this.type + " | " + this.description + " | " + at.getDatetime();
    }
}