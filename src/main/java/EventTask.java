public class EventTask extends Task {
    protected String deadline;

    public EventTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + "(by: " + this.deadline + ")";
    }
}
