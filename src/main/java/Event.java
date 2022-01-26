public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (at: " + at + ")";
    }

    @Override
    public String getFileString() {
        return "D|" + (isDone == true ? "1|" : "0|") + getDescription() + "|" + getAt() + "\n";
    }
}
