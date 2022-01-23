import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate startTime;

    public Event(String description, LocalDate time) {
        super(description);
        this.startTime = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]")
                .append(super.toString())
                .append(" (at: ")
                .append(this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                .append(")");
        return sb.toString();
    }
}
