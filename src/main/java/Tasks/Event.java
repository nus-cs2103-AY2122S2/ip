package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime dateTime;

    public Event(String description, LocalDateTime dateTime) {
        super("E", description);
        this.dateTime = dateTime;
    }

    public String toString() {
        return super.toString() + "(at: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

    @Override
    public String dBText() {
        String complete = this.getCompleted() ? "1" : "0";
        return String.format("E|%s|%s|%s", complete, this.getDescription(), this.dateTime.toString());
    }

}
