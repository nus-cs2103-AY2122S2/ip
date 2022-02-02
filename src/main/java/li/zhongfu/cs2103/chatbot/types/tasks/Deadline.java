package li.zhongfu.cs2103.chatbot.types.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

public class Deadline extends Task {
    private LocalDateTime eventTime;

    public Deadline(String name, LocalDateTime eventTime) {
        super(name);
        this.eventTime = eventTime;
    }

    public LocalDateTime getDeadline() {
        return this.eventTime;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getDone() ? "X" : " ", this.getName(), this.getDeadline().format(DATE_TIME_FORMAT));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline deadline = (Deadline) o;
            return super.equals(deadline) && this.eventTime.equals(deadline.eventTime);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.eventTime);
    }
}
