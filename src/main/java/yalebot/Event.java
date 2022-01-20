package yalebot;

public class Event extends Task {
    protected String at;

    public Event(String name, boolean isMarked, String at) {
        super(name, isMarked);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")"   ;
    }
}
