package li.zhongfu.cs2103.chatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime eventTime;

    public Deadline(String name, LocalDateTime eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    public LocalDateTime getDeadline() {
        return this.eventTime;
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getDone() ? "X" : " ", this.getName(), this.getDeadline().format(DATE_TIME_FORMAT));
    }
}
