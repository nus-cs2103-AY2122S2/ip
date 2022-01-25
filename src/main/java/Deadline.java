import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime dead;
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm");

    public Deadline(String name, LocalDateTime dead) {
        super("D", name, dead);
        this.dead = dead;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dead.format(outputFormatter) + ")";
    }
}
