package dazz.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private static final String TYPE = "E";
    private final LocalDateTime dateTime;

    public Event(String description, LocalDateTime date) {
        super(description);
        this.dateTime = date;
    }

    public Event(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone);
        this.dateTime = date;
    }

    private String getDateTimeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd MMM yyyy, hh:mma]");
        return this.dateTime.format(formatter);
    }

    @Override
    public String writeToFile() {
        return TYPE + " === " + super.writeToFile() + " === " + this.getDateTimeFormat();
    }

    @Override
    public String toString() {
        return "[" + TYPE + "]" + super.toString() + " (by: " + this.getDateTimeFormat() + ")";
    }
}