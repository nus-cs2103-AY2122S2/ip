import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Event extends Task {
    private LocalDateTime at;

    public Event(String task, LocalDateTime at) {
        super(TaskType.EVENT, task);
        this.at = at;
    }

    public LocalDateTime getAt() {
        return this.at;
    }

    public Optional<LocalDateTime> getDate() {
        return Optional.of(this.at);
    }

    @Override
    public String getReadableString() {
        return String.format("%s (at: %s)", super.getReadableString(),
                this.at.format(DateTimeFormatter.ofPattern("d MMM yyyy '-' hh:mm a")));
    }
}
