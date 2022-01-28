import java.time.LocalDate;
import  java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate at;

    /**
     * Constructor to create an Event Task.
     */
    public Event(String name, String at) {
        super(name);
        at = at.trim();
        this.at = LocalDate.parse(at);
    }

   
    public Event(String name, LocalDate at, boolean done) {
        super(name, done);
        this.at = at;
    }

    /**
     * Getter for the date of the Event.
     * @return The date of the Event.
     */
    public LocalDate getAt() {
        return this.at;
    }

    @Override
    public String toString() {
         return "[E]" + super.toString() + "(at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}