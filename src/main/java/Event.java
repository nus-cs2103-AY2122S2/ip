public class Event extends Task {
    protected String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at.substring(at.indexOf(" ")+1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
