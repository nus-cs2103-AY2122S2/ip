import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate at;
    protected String type;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
        this.type = "event";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(super.outputDateFormat) + ")";
    }
}