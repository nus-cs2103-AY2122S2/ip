public class Event extends Task{
    String time;
    Event(String item, String time) {
        super(item);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}
