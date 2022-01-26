public class Event extends Task {
    public String atTime;

    public Event(String description, boolean isMarked, String atTime) {
        super(description, isMarked);
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.atTime + ")";
    }

    @Override
    public String toData() {
        return "E | " + (this.isMarked ? 1 : 0) + " | " + this.description + " | " + this.atTime;
    }
}
