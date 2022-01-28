package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    public Event(String description, String start, String end) {
        super(description, Type.EVENT);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime startDate = LocalDateTime.parse(start, format);
        LocalDateTime endDate = LocalDateTime.parse(end, format);

        this.startTime = startDate;
        this.endTime = endDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getStartTimeString(String formatString) {
        return getStartTime().format(DateTimeFormatter.ofPattern(formatString));
    }

    public String getStartTimeString() {
        return getStartTimeString("dd/MM/yyyy HHmm");
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
                + "(at: "
                + getStartTimeString()
                +" till "
                + getEndTimeString()
                + ")";
    }
}