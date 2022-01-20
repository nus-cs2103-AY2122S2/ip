public class Event extends Task {
    protected String at;

    public Event(String s, String at) {
        super(s);
        this.at = at;
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
} 