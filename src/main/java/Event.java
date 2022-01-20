public class Event extends Task {
    private final String at;

    Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + "(at: " + at + ")");
    }
}
