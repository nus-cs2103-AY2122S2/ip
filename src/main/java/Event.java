import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    protected LocalDateTime at;

    public Event(boolean isDone, String description, String at) throws DateTimeParseException {
        super(description);
        this.at = DateUtil.stringToDate(at);
        this.isDone = isDone;
    }

    public Event(String description, String by) throws DateTimeParseException {
        this(false, description, by);
    }

    @Override
    public String appendtoFile() {
        return "E|" + (super.isDone ? "1" : "0") + "|" + super.description + "|" + DateUtil.dateToString(at) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateUtil.dateToString(at) + ")";
    }
}
