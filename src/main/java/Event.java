/**
 * A class that creates Event objects with at variable,
 * that stores the data/time the event will occur.
 */
public class Event extends Task {
    private final String at;

    public Event(String e, String at) {
        super(e);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(At: " + this.at + ")";
    }
}
