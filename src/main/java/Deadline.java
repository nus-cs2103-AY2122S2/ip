import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate time;

    public Deadline (String task, boolean done, LocalDate time) {
        super(task, done);
        this.time = time;
    }

    public String toString() {
        return String.format("[D][%s] %s (by %s)", this.done ? "X" : " ", this.task, this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}