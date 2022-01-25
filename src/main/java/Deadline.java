import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;

    public Deadline(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    @Override
    public String printStatus() {
        return "[D] " + Task.statusSymbols[super.getStatus()] + " " + this.toString() + " (by: " +
                date.format(DateTimeFormatter.ofPattern("dd MMM yyy HH:mm")) + ")";
    }

    @Override
    public String toString() {
        return super.getName();
    }
}