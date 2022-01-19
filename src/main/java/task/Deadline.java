package task;

public class Deadline extends Task {
    private String time;
    
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }
    
    public String getTime() {
        return this.time;
    }
    
    @Override
    public String getTaskIcon() {
        return "[D]";
    }
    
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getTime() + ")";
    }
}
