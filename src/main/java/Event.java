public class Event extends Task{
    protected String on;
    public Event(String title,String on) {
        super(title);
        this.on = on;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + on + ")";
    }
}
