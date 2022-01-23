public class Event extends Task{
    protected String dead;

    public Event(String name, String dead) {
        super("E", name, dead);
        this.dead = dead;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + dead + ")";
    }
}
