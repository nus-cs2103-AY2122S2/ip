package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description, TaskType.EVENT);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("%s (at: %shrs)", super.toString(), time.format(format));
    }

    @Override
    public String getFileSaveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return String.format("%s | %s", super.getFileSaveFormat(), time.format(format));
    }
}
