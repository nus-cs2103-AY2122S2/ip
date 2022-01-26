package Task;

public class Event extends Task {
    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + ")";
    }

    public String toSave() {
        return "E" + super.toSave() + " : " + by;
    }
}