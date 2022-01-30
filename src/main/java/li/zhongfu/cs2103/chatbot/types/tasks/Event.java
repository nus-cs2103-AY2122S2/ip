package li.zhongfu.cs2103.chatbot.types.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event extends Task {
    private LocalDateTime eventTime;

    public Event(String name, LocalDateTime eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    public LocalDateTime getEventTime() {
        return this.eventTime;
    }

    public String toString() {
        return String.format("[E][%s] %s (at: %s)", this.getDone() ? "X" : " ", this.getName(), this.getEventTime().format(DATE_TIME_FORMAT));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event event = (Event) o;
            return super.equals(event) && this.eventTime.equals(event.eventTime);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.eventTime);
    }
}
