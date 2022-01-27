import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime endTime;

    public Deadline(String description, LocalDateTime end) {
        super(description, Type.DEADLINE);
        this.endTime = end;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getEndTimeString(String formatString) {
        return getEndTime().format(DateTimeFormatter.ofPattern(formatString));
    }

    public String getEndTimeString() {
        return getEndTimeString("dd/MM/yyyy HHmm");
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(by: "
                + getEndTimeString()
                + ")";
    }
}
