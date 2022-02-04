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

    public String outputTime() {
        return time.format(outputFormatter);
    }

    public String displayTimeInOriginalFormat() {
        return time.format(inputFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + outputTime() + ")";
    }
}
