package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    public String preposition;
    LocalDateTime dateTime;

    public EventTask(String name, String preposition, LocalDateTime dateTime) {
        super(name);
        this.preposition = preposition;
        this.dateTime = dateTime;
    }

    public String getPreposition() {
        return this.preposition;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public String toString() {
        return "[E]" + super.toString() + String.format(" (%s: %s)", this.preposition, this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
