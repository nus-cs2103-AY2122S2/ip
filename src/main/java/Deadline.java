import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDate dateTime;

    LocalDate getDateTime() {
        return dateTime;
    }

    Deadline(String description, LocalDate dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
