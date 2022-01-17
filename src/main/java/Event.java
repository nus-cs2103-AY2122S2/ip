public class Event extends Task {
    String timing;

    public Event (String task, String timing) {
        super(task);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + this.timing + ")";
    }
}
