public class Event extends Task{
    protected String on;
    public Event(String title,String on) {
        super(title);
        this.on = on;
    }

    public Event(String title, boolean done, String on) {
        super(title, done);
        this.on = on;
    }

    public String getDate() {
        return on;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + on + ")";
    }


}
