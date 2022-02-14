package duke.tasks;

import java.time.LocalDateTime;

public class Event extends Task{

    private LocalDateTime date;

    public Event(String content, LocalDateTime date) {
        super(content);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date);
    }
}
