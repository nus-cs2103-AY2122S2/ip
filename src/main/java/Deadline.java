import java.util.Map;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.stripTrailing();
    }

    public Deadline(Map<String, String> args) {
        this(args.get("description"), args.get("by"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getCommandString() {
        return String.format("deadline %s /by %s", description, by);
    }
}