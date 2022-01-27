package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDateTime time;


    public Deadline(String description, LocalDateTime time){
        super(description);
        this.time = time;
    }
    public LocalDateTime getTime(){
        return this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
