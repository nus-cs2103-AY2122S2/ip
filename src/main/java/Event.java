import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private String date;
    private String time;

    public Event(String content, String when) {
        super(content);
        setDateAndTime(when);
    }

    public Event(String content, String when, boolean done) {
        super(content, done);
        setDateAndTime(when);
    }

    public Event(String content, boolean done) {
        super(content, done);
    }

    public static Event createEventFromStorage(String content, String when, boolean done) {
        Event event = new Event(content, done);
        parseDateAndTime(event, when);
        return event;
    }

    private static void parseDateAndTime(Event event, String when) {
        event.date = when.substring(0, 11);
        event.time = when.substring(12);
    }

    public void setDateAndTime(String when) {
        String[] dt = when.split(" ");
        LocalDate date = LocalDate.parse(dt[0]);
        LocalTime time = LocalTime.parse(dt[1]);
        this.date = date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        this.time = time.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + (super.done? "[X] " : "[ ] ") + super.content + " (at " + this.date + " " + this.time + ")";
    }

    @Override
    public String toFileString() {
        return "E, " + (super.done ? "1, " : "0, ") + super.content + ", " + this.date + " " + this.time;
    }
}
