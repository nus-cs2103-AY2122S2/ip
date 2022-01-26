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
    public String getFullDetails() {
        return (super.getFullDetails() + "(at: " + this.eventDetails + ")");
    }
}
