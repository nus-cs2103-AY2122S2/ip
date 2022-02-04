import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final DateTimeFormatter inputFormatterStart = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter inputFormatterEnd = DateTimeFormatter.ofPattern("HHmm");
    private final DateTimeFormatter outputFormatterStart = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm -");
    private final DateTimeFormatter outputFormatterEnd = DateTimeFormatter.ofPattern(" HH:mm");
    protected LocalDateTime dateAndStartTime;
    protected LocalTime endTime;

    public Event(String description, String dateAndStartTime, String endTime) {
        super(description);
        this.dateAndStartTime = LocalDateTime.parse(dateAndStartTime, inputFormatterStart);
        this.endTime = LocalTime.parse(endTime, inputFormatterEnd);
    }

    public String outputTime() {
        return dateAndStartTime.format(outputFormatterStart)
                + endTime.format(outputFormatterEnd);
    }

    public String displayTimeInOriginalFormat() {
        return dateAndStartTime.format(inputFormatterStart) + " - "
                + endTime.format(inputFormatterEnd);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + outputTime() + ")";
    }
}
