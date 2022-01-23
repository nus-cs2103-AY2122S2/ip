import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    String preposition;
    LocalDateTime dateTime;

    EventTask(String name,String preposition, LocalDateTime dateTime) {
        super(name);
        this.preposition = preposition;
        this.dateTime = dateTime;
    }

    public String toString() {
        return "[E]" + super.toString() + String.format(" (%s: %s)",this.preposition, this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
