public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getSaveToFileFormat() {
        return "E," + super.getSaveToFileFormat() + "," + at + "," + getStatusIcon();
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}