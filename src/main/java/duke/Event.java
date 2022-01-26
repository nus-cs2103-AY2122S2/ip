package duke;
public class Event extends Task {
    protected String at;
    char type;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = 'e';
    }

    public String toString() {
        return "[E]" + super.toString() + " (" + at + ")";
    }
}
