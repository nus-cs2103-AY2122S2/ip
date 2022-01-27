import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description, false);
        String[] dateTime = by.split(" ");
        this.by = LocalDateTime.of(LocalDate.parse(dateTime[0]), LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm")));
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    public String formatData() {
        return "D|" + super.formatData() + "|" + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}
