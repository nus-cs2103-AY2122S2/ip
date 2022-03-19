import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate dateTime;
    private final String dateTimeStr;

    LocalDate getDateTime() {
        return dateTime;
    }

    Event(String description, String dateTimeStr) {
        super(description);
        this.dateTimeStr = dateTimeStr;
        this.dateTime = LocalDate.parse(dateTimeStr);
    }

    Event(String description, String dateTimeStr, boolean isDone) {
        super(description, isDone);
        this.dateTimeStr = dateTimeStr;
        this.dateTime = LocalDate.parse(dateTimeStr);
    }

    @Override
    public String toWrite() {
        int bool;
        if(isDone) {
            bool = 1;
        } else {
            bool = 0;
        }

        return "E~" + bool + "~" + getDescription() + "~" +
                dateTimeStr + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
