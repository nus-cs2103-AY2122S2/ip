public class Event extends TaskWithDateTime {
    protected String at;

    public Event(String description, String at) {
        super(description, at);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }

    @Override
    public String writeToFile() {
        return "E | " + super.writeToFile() + " | " + at;
    }
}
