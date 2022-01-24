public class Event extends Task {
    private String at;

    public Event(String description, String time) {
        super(description, Type.EVENT);
        this.at = time;
    }

    public String getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }
}
