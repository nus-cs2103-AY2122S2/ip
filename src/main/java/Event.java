import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime dead;
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm");

    public Event(String name, LocalDateTime dead) {
        super(name);
        this.dead = dead;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dead.format(outputFormatter) + ")";
    }
}
