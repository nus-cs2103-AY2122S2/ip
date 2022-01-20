public class Event extends Task{
    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String toString() {
        String mark;
        if (super.getDone()) {
            mark = "[X] ";
        } else {
            mark = "[ ] ";
        }
        return "[E]" + mark + getName() +
                " (at: " + this.time + ")";
    }
}
