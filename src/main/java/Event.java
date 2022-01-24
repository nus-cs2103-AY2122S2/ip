import java.util.Map;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at.stripTrailing();
    }

    public Event(Map<String, String> args) {
        this(args.get("description"), args.get("at"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getCommandString() {
        return String.format("event %s /at %s", description, at);
    }
}
