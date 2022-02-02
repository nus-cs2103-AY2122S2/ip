import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    protected LocalDateTime time;

    public Deadline(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, inputFormatter);
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + time.format(outputFormatter) + ")";
    }
}
