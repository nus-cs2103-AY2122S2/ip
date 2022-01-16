public class Event extends Task {
    String time;
    public Event(String description, String time) {
        super(description, TaskType.EVENT);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.time);
    }
}
