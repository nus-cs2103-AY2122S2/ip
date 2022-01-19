public class Event extends Task {
    protected String at;

    Event(String description, int id, String at) {
        super(description, id);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
