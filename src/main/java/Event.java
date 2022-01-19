public class Event extends Task {
    String type = "[E]";
    String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.description + "(" + time + ")";
    }

    @Override
    public String track() {
        return this.type;
    }
}
