public class Event extends Task {

    protected String at;  
    
    public Event(String description, String at, boolean done) {
        super(description, done);
        this.at = at; 
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    @Override
    public String toStringSaveData() {
        return String.join(" | ", "E", String.valueOf(done ? 1 : 0), description, at);
    }

}
