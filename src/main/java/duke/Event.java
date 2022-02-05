package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime time;


    public Event(String taskName, String time) {
        super(taskName);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
        this.time = LocalDateTime.parse(time, format);
    }

    public Event(String taskName, String time, boolean done) {
        super(taskName, done);
        DateTimeFormatter format = DateTimeFormatter.ofPattern(" yyyy-MM-dd HHmm");
        this.time = LocalDateTime.parse(time, format);
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        return "[E]" + super.toString() + "(at:" + time.format(displayFormat) + ")";
    }
}
