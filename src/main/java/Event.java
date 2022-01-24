import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    NotableDate date;
    LocalTime time;

    public Event(String des, NotableDate date) {
        super(des, TaskType.EVENT);
        this.date = date;
    }

    public Event(String des, NotableDate date, LocalTime time) {
        super(des, TaskType.EVENT);
        this.date = date;
        this.time = time;
    }


    @Override
    public String toString() {
        String str;
        if (isDone) {
            str = "[E][X] " + description + " (at: " + date;
        } else {
            str = "[E][ ] " + description + " (at: " + date;
        }

        if (time != null) {
            str += " " + time.format(DateTimeFormatter.ofPattern("h:mma"));
        }
        str += ")";

        return str;
    }
}