package Alfred.Task;

import java.time.LocalDateTime;

public class Event extends Task {
    String type = "E";
    LocalDateTime dateTime;

    public Event(String description, String dateAndTime) {
        super(description);
        this.dateTime = LocalDateTime.parse(dateAndTime);
        ;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString() + " (at: " +
                super.localDateTimeToString(this.dateTime) + ")";
    }
}