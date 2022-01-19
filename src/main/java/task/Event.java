package task;

public class Event extends Task {
    private String time;
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }
    
    public String getTime() {
        return this.time;
    }
    
    @Override
    String getTaskIcon() {
        return "[E]";
    }
    
    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getTime() + ")";
    }
}
