package puke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String taskName, LocalDateTime date) {
        super(taskName);
        this.at = date;
    }

    public String toSaveString() {
        return "E@@" + (this.isDone() ? "1@@" : "0@@")
                + this.name + "@@" + this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)", this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
