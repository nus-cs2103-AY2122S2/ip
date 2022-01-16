public class Event extends Task{
    protected String scheduledTime;

    public Event(String description, String scheduledTime) {
        super(description);
        this.scheduledTime = scheduledTime;
    }

    /*
     * Customized toString method for Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.scheduledTime + ")";
    }
}
