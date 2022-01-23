public class Event extends Task {
    private String at;

    public Event(String description, String time) {
        super(description);
        this.at = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}
