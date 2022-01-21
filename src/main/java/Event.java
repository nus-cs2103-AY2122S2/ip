import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate time;

    public Event (String task, boolean done, LocalDate time) {
        super(task, done);
        this.time = time;
    }

    public String fileFormat() {
        return String.format("E | %s | %s | %s", this.done ? "X" : " ", this.task, this.time);
    }

    public String toString() {
        return String.format("[E][%s] %s (at %s)", this.done ? "X" : " ", this.task, this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}