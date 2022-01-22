public class Event extends Task {

    private String time;

    public Event(boolean completed, String task, String time) {
        super(task, completed);
        this.time = time;
    }

    public Event(String task, String time) {
        super(task);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}
