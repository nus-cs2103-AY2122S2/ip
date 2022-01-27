package main.java.duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Event extends Task {

    protected localDateTime at;

    public Event(String description, String at) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.at = LocalDateTime.parse(at, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    public String toFileFormat() {
        return "E," + String.valueOf(isDone) + "," + description + "," + at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
