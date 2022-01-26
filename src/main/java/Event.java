public class Event extends Task {
    protected String eventDetails;

    public Event(String taskName, String eventDetails) {
        super(taskName);
        this.eventDetails = eventDetails;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }
    
    @Override
    public String toDataString() {
        String isDone = super.isMarked() ? "1" : "0";
        return getTaskIcon() + " | " + isDone + " | " + taskName + " | " + eventDetails;
    }

    @Override
    public String toString() {
        return "[" + getTaskIcon() + "][" + super.getStatusIcon() + "] " + 
                super.taskName + " (at: " + this.eventDetails + ")";
    }
}
