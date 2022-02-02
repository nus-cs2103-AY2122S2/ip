import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate time;

    Event(String item, LocalDate time) {
        super(item);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), 
            this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
