package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event for task class
 */
public class Event extends Task {
    public LocalDateTime time;

    public Event(String description, LocalDateTime time){
        super(description);
        this.time = time;
    }

    public LocalDateTime getTime(){
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " at " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
