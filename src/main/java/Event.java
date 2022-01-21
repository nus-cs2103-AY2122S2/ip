import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime on;
    public Event(String title,LocalDateTime on) {
        super(title);
        this.on = on;
    }

    public Event(String title, boolean done, LocalDateTime on) {
        super(title, done);
        this.on = on;
    }

    public LocalDateTime getDate() {
        return on;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + Parser.printDate(on) + ")";
    }
}
