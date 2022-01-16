public class Event extends Task {

    public String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + this.duration +  ")";
    }
}
