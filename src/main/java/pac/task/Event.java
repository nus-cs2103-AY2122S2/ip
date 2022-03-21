package pac.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * For event tasks
 */
public class Event extends Task {
    private LocalDate dateTime;
    private String dateTimeStr;

    LocalDate getDateTime() {
        return dateTime;
    }

    public Event(String description, String dateTimeStr) {
        super(description);
        this.dateTimeStr = dateTimeStr;
        this.dateTime = LocalDate.parse(dateTimeStr);
    }

    public Event(String description, String dateTimeStr, boolean isDone) {
        super(description, isDone);
        this.dateTimeStr = dateTimeStr;
        this.dateTime = LocalDate.parse(dateTimeStr);
    }

    /**
     * Reschedules the date
     * @param dateTimeStr
     */
    @Override
    public void rescheduleDate(String dateTimeStr) {
        this.dateTimeStr = dateTimeStr;
        this.dateTime = LocalDate.parse(dateTimeStr);
    }

    /**
     * Returns the String that is written into data file
     * @return
     */
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
        return "[E]" + super.toString() + " (at: " + dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
