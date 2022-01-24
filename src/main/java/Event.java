import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected String time;
    protected LocalDate ld;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }
    public Event(String description, String time, boolean isDone) {
        super(description);
        this.time = time;
        this.isDone = isDone;
    }

    public Event(String description, LocalDate ld) {
        super(description);
        this.ld = ld;
    }

    @Override
    public String toString() {
        if(ld == null) {
            return "[E]" + super.toString() + " (by: " + time + ")";
        } else {
            return "[E]" + super.toString() + " (by: " + ld.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy")) + ")";
        }
    }

    @Override
    public String getDetail(){
        int status = isDone ? 1 : 0;
        return "E" + " | " + status + " | " + this.description + " | " + this.time + "\n";
    }
}
