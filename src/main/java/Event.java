public class Event extends Task{
    protected String scheduledTime;

    public Event(String description, String scheduledTime) {
        super(description);
        this.scheduledTime = scheduledTime;
    }

    @Override
    public String formatSave() {
        return "E |" + (super.isDone ? "1| " : "0| ") +super.description +" /at " + scheduledTime;
    }

    /*
     * Customized toString method for Event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.scheduledTime + ")";
    }
}
