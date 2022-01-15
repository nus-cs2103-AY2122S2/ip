public class EventTask extends Task{
    String eventDate;

    public EventTask(String taskname, String eventDate) {
        super(taskname);
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDate + ")";
    }
}
