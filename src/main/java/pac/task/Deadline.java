package pac.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDate dateTime;
    private final String dateTimeStr;

    LocalDate getDateTime() {
        return dateTime;
    }

    public Deadline(String description, String dateTimeStr) {
        super(description);
        this.dateTimeStr = dateTimeStr;
        this.dateTime = LocalDate.parse(dateTimeStr);
    }

    public Deadline(String description, String dateTimeStr, boolean isDone) {
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

        return "D~" + bool + "~" + getDescription() + "~" +
                dateTimeStr + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
