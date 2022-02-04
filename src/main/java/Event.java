import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate at;
    public Event(String task, LocalDate at) {
        super(task.trim());
        this.at = at;
    }

    public Event(String task, boolean done) {
        super(task, done);
    }

    @Override
    public Event mark() {
        return new Event(task, true);
    }

    @Override
    public Event unmark() {
        return new Event(task, false);
    }

    @Override
    public LocalDate getDate() {
        return at;
    }

    @Override
    public String toString() {
        return "["+Type.E+"]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
