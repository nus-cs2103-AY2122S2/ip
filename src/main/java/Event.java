public class Event extends Task {

    public String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration.trim();
    }

    public Event(String description, String duration, boolean completed) {
        super(description, completed);
        this.duration = duration.trim();
    }

    public String toFile() {
        return "E -- " + super.toFile() + " -- " + this.duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + this.duration +  ")";
    }
}
