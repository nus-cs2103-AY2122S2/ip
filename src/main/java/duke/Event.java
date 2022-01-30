package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate time;

    public Event(String content, LocalDate time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DateTimeFormatter.BASIC_ISO_DATE) + ")";
    }

    @Override
    public String toData() {
        String isFinishedData;
        if (super.finished) {
            isFinishedData = "1";
        } else {
            isFinishedData = "0";
        }
        return "E:" + isFinishedData + ":" + super.content + ":" + time.format(DateTimeFormatter.ISO_DATE);
    }
}
