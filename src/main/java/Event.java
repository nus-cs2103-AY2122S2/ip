public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at.trim();
    }

    public boolean checkDestination() {
        return at.compareTo("") == 0 ? false : true;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}