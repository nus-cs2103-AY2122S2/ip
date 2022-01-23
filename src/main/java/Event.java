import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String task, LocalDateTime at) {
        super(TaskType.EVENT, task);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return this.at;
    }

    @Override
    public String getReadableString() {
        return String.format("%s (at: %s)", super.getReadableString(), this.at);
    }
}
